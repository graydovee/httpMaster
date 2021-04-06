package cn.graydove.httpmaster.starter.handler;

import cn.graydove.httpmaster.core.response.HttpResponse;

import java.lang.reflect.Method;

/**
 * @author graydove
 */
public interface AfterRequestHandler {

    Object handle(HttpResponse response, Method method, Object[] args);
}
