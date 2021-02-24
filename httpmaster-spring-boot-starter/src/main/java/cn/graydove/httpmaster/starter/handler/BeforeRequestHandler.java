package cn.graydove.httpmaster.starter.handler;

import cn.graydove.httpmaster.core.request.HttpRequest;

import java.lang.reflect.Method;

public interface BeforeRequestHandler {

    void handle(HttpRequest httpRequest, Method method, Object[] args);
}
