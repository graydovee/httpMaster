package cn.graydove.httpmaster.starter.handler.impl;

import cn.graydove.httpmaster.core.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 建议重写success和failure实现功能
 */
public abstract class AbstractAutoCloseAfterRequestHandler implements AbstractAfterRequestHandler {

    private final Logger logger = LoggerFactory.getLogger(AbstractAutoCloseAfterRequestHandler.class);

    @Override
    public final Object handle(HttpResponse response, Method method, Object[] args) {
        Object o = doHandle(response, method, args);
        if (null != o) {
            closeResponse(response);
        }
        return o;
    }

    private Object doHandle(HttpResponse response, Method method, Object[] args) {
        if (isOk(response)) {
            return success(response, method, args);
        }
        return failure(response, method, args);
    }

    protected void closeResponse(HttpResponse httpResponse) {
        try {
            httpResponse.close();
        } catch (IOException e) {
            logger.warn("close response error: " + e.getMessage(), e);
        }
    }

    @Override
    public Object success(HttpResponse response, Method method, Object[] args) {
        return null;
    }

    @Override
    public Object failure(HttpResponse response, Method method, Object[] args) {
        return null;
    }
}
