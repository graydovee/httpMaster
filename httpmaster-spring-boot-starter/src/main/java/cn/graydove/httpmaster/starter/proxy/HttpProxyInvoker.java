package cn.graydove.httpmaster.starter.proxy;

import cn.graydove.httpmaster.core.engine.HttpEngine;
import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.core.exception.HttpRequestException;
import cn.graydove.httpmaster.core.exception.UnsupportedException;
import cn.graydove.httpmaster.core.request.HttpRequest;
import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.starter.handler.AfterRequestHandler;
import cn.graydove.httpmaster.starter.handler.BeforeRequestHandler;
import cn.graydove.httpmaster.starter.handler.RequestFailureHandler;
import cn.graydove.httpmaster.starter.handler.RequestHandlerContext;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 代理方法执行器
 */
public class HttpProxyInvoker implements InvocationHandler {
    
    private Map<Method, HttpFunction> functionMap;
    
    private HttpEngine httpEngine;
    
    private RequestHandlerContext requestHandlerContext;

    public HttpProxyInvoker(Map<Method, HttpFunction> functionMap, HttpEngine httpEngine, RequestHandlerContext requestHandlerContext) {
        this.functionMap = functionMap;
        this.httpEngine = httpEngine;
        this.requestHandlerContext = requestHandlerContext;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        HttpFunction httpFunction = getHttpFunction(method);
        
        HttpRequest httpRequest = httpFunction.buildRequest(args);

        invokeBeforeHandler(httpRequest, method, args);

        HttpResponse response;
        try {
            response = invokeHttpRequest(httpFunction.getHttpMethod(), httpRequest);
        } catch (Throwable e) {
            Object o = invokeFailureHandler(e, method, args);
            if (null != o) {
                return o;
            }
            throw new HttpRequestException(e);
        }

        Object o = invokeAfterHandler(response, method, args);
        if (null != o) {
            return o;
        }
        
        return response;
    }

    private HttpFunction getHttpFunction(Method method) {
        HttpFunction httpFunction = functionMap.get(method);
        if (null == httpFunction) {
            throw new UnsupportedException(StrUtil.format("method not implement: {}", method.getName()));
        }
        return httpFunction;
    }

    private void invokeBeforeHandler(HttpRequest httpRequest, Method method, Object[] args) {
        for (BeforeRequestHandler beforeRequestHandler : requestHandlerContext.getBeforeRequestHandlerSet()) {
            beforeRequestHandler.handle(httpRequest, method, args);
        }
    }

    private HttpResponse invokeHttpRequest(HttpMethod httpMethod, HttpRequest httpRequest) {
        return httpEngine.request(httpMethod, httpRequest);
    }

    private Object invokeAfterHandler(HttpResponse response, Method method, Object[] args) {
        for (AfterRequestHandler afterRequestHandler : requestHandlerContext.getAfterRequestHandlerSet()) {
            Object o = afterRequestHandler.handle(response, method, args);
            if (null != o) {
                return o;
            }
        }
        return null;
    }
    
    private Object invokeFailureHandler(Throwable e, Method method, Object[] args) {
        for (RequestFailureHandler requestFailureHandler : requestHandlerContext.getRequestFailureHandlerSet()) {
            Object o = requestFailureHandler.handle(e, method, args);
            if (null != o) {
                return o;
            }
        }
        return null;
    }

}
