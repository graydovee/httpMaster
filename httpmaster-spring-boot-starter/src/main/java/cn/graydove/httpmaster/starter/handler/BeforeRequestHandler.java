package cn.graydove.httpmaster.starter.handler;

import cn.graydove.httpmaster.core.request.HttpRequest;

import java.lang.reflect.Method;

/**
 * @author graydove
 */
public interface BeforeRequestHandler {

    /**
     * 返回false可终止请求
     */
    boolean handle(HttpRequest httpRequest, Method method, Object[] args);
}
