package cn.graydove.httpmaster.starter.handler;

import cn.graydove.httpmaster.core.response.HttpResponse;

import java.lang.reflect.Method;

public interface AfterRequestHandler {

    Object handle(HttpResponse response, Method method, Object[] args);
}
