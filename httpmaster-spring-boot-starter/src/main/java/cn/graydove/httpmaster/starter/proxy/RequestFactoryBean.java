package cn.graydove.httpmaster.starter.proxy;

import cn.graydove.httpmaster.core.engine.HttpEngine;
import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.starter.annotation.*;
import cn.graydove.httpmaster.starter.annotation.Header;
import cn.graydove.httpmaster.starter.annotation.Query;
import cn.graydove.httpmaster.starter.annotation.method.Http;
import cn.graydove.httpmaster.starter.bean.ParamDefinition;
import cn.graydove.httpmaster.starter.bean.RequestDefinition;
import cn.graydove.httpmaster.starter.enums.HttpBodyStrategy;
import cn.graydove.httpmaster.starter.handler.RequestHandlerContext;
import cn.graydove.httpmaster.starter.annotation.Body;
import cn.graydove.httpmaster.starter.annotation.HttpService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.net.url.UrlBuilder;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author graydove
 */
public class RequestFactoryBean<T> implements FactoryBean<T> {

    private Class<T> clazz;

    private HttpRequestFactory httpRequestFactory;

    private HttpEngine httpEngine;

    private RequestHandlerContext requestHandlerContext;

    public RequestFactoryBean(Class<T> clazz, HttpRequestFactory httpRequestFactory, HttpEngine httpEngine, RequestHandlerContext requestHandlerContext) {
        this.clazz = clazz;
        this.httpRequestFactory = httpRequestFactory;
        this.httpEngine = httpEngine;
        this.requestHandlerContext = requestHandlerContext;
    }

    @Override
    public T getObject() throws Exception {
        return createProxy();
    }

    @Override
    public Class<?> getObjectType() {
        return clazz;
    }

    /**
     * 创建代理对象
     * @return 代理对象
     */
    @SuppressWarnings("unchecked")
    private T createProxy() {
        Map<Method, HttpFunction> functionMap = createFunctionMap();
        InvocationHandler h = new HttpProxyInvoker(functionMap, httpEngine, requestHandlerContext);
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, h);
    }


    private Map<Method, HttpFunction> createFunctionMap() {
        HttpService annotation = AnnotationUtils.findAnnotation(clazz, HttpService.class);
        Map<Method, HttpFunction> methodMap = new HashMap<>();

        for (Method method : clazz.getMethods()) {
            RequestDefinition requestDefinition = new RequestDefinition();

            requestDefinition.setHttpRequestFactory(httpRequestFactory);

            Http http = AnnotatedElementUtils.findMergedAnnotation(method, Http.class);
            if (null == http) {
                continue;
            }
            // 1. method
            HttpMethod httpMethod = http.method();
            requestDefinition.setHttpMethod(httpMethod);

            // 2. url
            String url;
            url = http.url();
            if (StrUtil.isBlank(url)) {
                url = Objects.requireNonNull(annotation).url();
            }

            UrlBuilder urlBuilder = UrlBuilder.ofHttp(url, StandardCharsets.UTF_8);
            String path = http.path();
            if (StrUtil.isNotBlank(path)) {
                for (String p : StrUtil.split(path, '/')) {
                    urlBuilder.appendPath(p);
                }
            }
            url = urlBuilder.build();
            requestDefinition.setUrl(url);

            // 解析参数的注解
            Parameter[] parameters = method.getParameters();
            List<ParamDefinition> queryDefinitionList = new ArrayList<>();
            List<ParamDefinition> headerDefinitionList = new ArrayList<>();
            List<ParamDefinition> bodyDefinitionList = new ArrayList<>();
            Integer urlPos = null;
            for (int i=0; i<parameters.length; ++i) {
                boolean proceed = false;
                Parameter parameter = parameters[i];
                Header header = AnnotationUtils.findAnnotation(parameter, Header.class);
                if (null != header) {
                    proceed = true;
                    String name = header.value();
                    if (StrUtil.isBlank(name)) {
                        name = parameter.getName();
                    }
                    headerDefinitionList.add(ParamDefinition.of(name, i));
                }
                Query query = AnnotationUtils.findAnnotation(parameter, Query.class);
                if (null != query) {
                    proceed = true;
                    String name = query.value();
                    if (StrUtil.isBlank(name)) {
                        name = parameter.getName();
                    }
                    queryDefinitionList.add(ParamDefinition.of(name, i));
                }
                Body body = AnnotationUtils.findAnnotation(parameter, Body.class);
                if (null != body) {
                    proceed = true;
                    String name = body.value();
                    if (StrUtil.isBlank(name)) {
                        name = parameter.getName();
                    }
                    bodyDefinitionList.add(ParamDefinition.of(name, i));
                }

                // 没有注解默认处理：能放body放body否则放query
                if (!proceed && http.httpBodyStrategy() != HttpBodyStrategy.NONE && http.method().isHasBody()) {
                    bodyDefinitionList.add(ParamDefinition.of(parameter.getName(), i));
                } else {
                    queryDefinitionList.add(ParamDefinition.of(parameter.getName(), i));
                }

                //url参数
                Url requestUrl = AnnotationUtils.findAnnotation(parameter, Url.class);
                if (requestUrl != null) {
                    urlPos = i;
                }
            }
            requestDefinition.setUrlPos(urlPos);

            // 3,4,5. params
            requestDefinition.setHeaderDefinitions(headerDefinitionList);
            requestDefinition.setQueryDefinitions(queryDefinitionList);
            requestDefinition.setBodyDefinitions(bodyDefinitionList);

            // 6. HttpBodyStrategy
            HttpBodyStrategy strategy;
            if (httpMethod.isHasBody()) {
                if (CollectionUtil.isEmpty(bodyDefinitionList)) {
                    strategy = HttpBodyStrategy.NONE;
                } else {
                    strategy = http.httpBodyStrategy();
                }
            } else {
                strategy = HttpBodyStrategy.NONE;
            }
            requestDefinition.setHttpBodyStrategy(strategy);

            methodMap.put(method, new HttpFunction(requestDefinition));
        }
        return methodMap;
    }
}
