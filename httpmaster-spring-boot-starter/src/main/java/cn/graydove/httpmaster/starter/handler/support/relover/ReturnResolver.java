package cn.graydove.httpmaster.starter.handler.support.relover;

import cn.graydove.httpmaster.core.response.HttpResponse;

import java.lang.reflect.Method;


public interface ReturnResolver {

    boolean isSupport(Class<?> returnType);

    Object resolve(HttpResponse httpResponse, Method method);

}
