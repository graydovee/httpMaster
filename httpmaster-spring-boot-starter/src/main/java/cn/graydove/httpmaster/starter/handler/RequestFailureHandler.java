package cn.graydove.httpmaster.starter.handler;

import java.lang.reflect.Method;

/**
 * @author graydove
 */
public interface RequestFailureHandler {

    Object handle(Throwable e, Method method, Object[] args);
}
