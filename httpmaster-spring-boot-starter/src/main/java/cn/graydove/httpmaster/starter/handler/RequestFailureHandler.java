package cn.graydove.httpmaster.starter.handler;

import java.lang.reflect.Method;

public interface RequestFailureHandler {

    Object handle(Throwable e, Method method, Object[] args);
}
