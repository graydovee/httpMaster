package cn.graydove.httpmaster.starter.handler.support;

import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.starter.handler.AfterRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

public abstract class AbstractAutoCloseRequestHandler implements AfterRequestHandler {

    private final Logger logger = LoggerFactory.getLogger(AbstractAutoCloseRequestHandler.class);

    @Override
    public Object handle(HttpResponse response, Method method, Object[] args) {
        Object o = doHandle(response, method, args);
        if (null != o) {
            closeResponse(response);
        }
        return o;
    }

    public abstract Object doHandle(HttpResponse response, Method method, Object[] args);

    protected void closeResponse(HttpResponse httpResponse) {
        try {
            httpResponse.close();
        } catch (IOException e) {
            logger.warn("close response error: " + e.getMessage(), e);
        }
    }
}
