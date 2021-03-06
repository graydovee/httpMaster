package cn.graydove.httpmaster.starter.proxy;

import cn.graydove.httpmaster.core.engine.HttpEngine;
import cn.graydove.httpmaster.core.exception.HttpRequestException;
import cn.graydove.httpmaster.core.exception.UnsupportedException;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.starter.handler.AfterRequestHandler;
import cn.graydove.httpmaster.starter.handler.BeforeRequestHandler;
import cn.graydove.httpmaster.starter.handler.RequestFailureHandler;
import cn.graydove.httpmaster.starter.handler.RequestContext;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 代理方法执行器
 * @author graydove
 */
public class HttpProxyInvoker implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(HttpProxyInvoker.class);
    
    private Map<Method, HttpFunction> functionMap;
    
    private RequestContext requestContext;

    public HttpProxyInvoker(Map<Method, HttpFunction> functionMap, RequestContext requestContext) {
        this.functionMap = functionMap;
        this.requestContext = requestContext;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        HttpRequest httpRequest = getHttpFunction(method, args);

        if (invokeBeforeHandler(httpRequest, method, args)) {
            if (logger.isDebugEnabled()) {
                logger.debug("request cancel");
            }
            return null;
        }

        HttpResponse response;
        try {
            response = invokeHttpRequest(httpRequest);
        } catch (Throwable e) {
            Object o = invokeFailureHandler(e, method, args);
            if (null != o) {
                return o;
            }
            throw new HttpRequestException(e);
        }

        Object o = invokeAfterHandler(response, method, args);

        return null == o ? response : o;
    }

    private HttpRequest getHttpFunction(Method method, Object[] args) {
        HttpFunction httpFunction = functionMap.get(method);
        if (null == httpFunction) {
            throw new UnsupportedException(StrUtil.format("method not implement: {}", method.getName()));
        }
        return httpFunction.buildRequest(args);
    }

    private boolean invokeBeforeHandler(HttpRequest httpRequest, Method method, Object[] args) {
        for (BeforeRequestHandler beforeRequestHandler : requestContext.getBeforeRequestHandlerList()) {
            boolean f = beforeRequestHandler.handle(httpRequest, method, args);
            if (!f) {
                return true;
            }
        }
        return false;
    }

    private HttpResponse invokeHttpRequest(HttpRequest httpRequest) {
        return requestContext.getHttpEngine().execute(httpRequest);
    }

    private Object invokeAfterHandler(HttpResponse response, Method method, Object[] args) {
        for (AfterRequestHandler afterRequestHandler : requestContext.getAfterRequestHandlerList()) {
            Object o = afterRequestHandler.handle(response, method, args);
            if (null != o) {
                return o;
            }
        }
        return null;
    }
    
    private Object invokeFailureHandler(Throwable e, Method method, Object[] args) {
        for (RequestFailureHandler requestFailureHandler : requestContext.getRequestFailureHandlerList()) {
            Object o = requestFailureHandler.handle(e, method, args);
            if (null != o) {
                return o;
            }
        }
        return null;
    }

}
