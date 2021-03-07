package cn.graydove.httpmaster.starter.handler.support;

import cn.graydove.httpmaster.core.exception.HttpException;
import cn.graydove.httpmaster.starter.exception.BadRequestException;
import cn.graydove.httpmaster.starter.handler.RequestFailureHandler;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Method;

public class ExceptionRequestFailureHandler implements RequestFailureHandler {

    @Override
    public Object handle(Throwable e, Method method, Object[] args) {
        throw new HttpException(StrUtil.format("request error, error message: {}, method: {}, args: {}", e.getMessage(), method.getName(), args));
    }

}
