package cn.graydove.httpmaster.starter.handler.impl;

import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.starter.handler.AfterRequestHandler;
import cn.hutool.http.HttpStatus;

import java.lang.reflect.Method;

/**
 * @author graydove
 */
public interface AbstractAfterRequestHandler extends AfterRequestHandler {

    default boolean isOk(HttpResponse response) {
        return HttpStatus.HTTP_OK == response.getStatusCode();
    }

    @Override
    default Object handle(HttpResponse response, Method method, Object[] args) {
        if (isOk(response)) {
            return success(response, method, args);
        }
        return failure(response, method, args);
    }

    Object success(HttpResponse response, Method method, Object[] args);

    Object failure(HttpResponse response, Method method, Object[] args);
}
