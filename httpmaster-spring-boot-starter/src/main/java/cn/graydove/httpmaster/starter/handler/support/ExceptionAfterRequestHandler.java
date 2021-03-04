package cn.graydove.httpmaster.starter.handler.support;

import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.starter.exception.BadRequestException;
import cn.hutool.core.util.StrUtil;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

@Order
public class ExceptionAfterRequestHandler extends AbstractAutoCloseAfterRequestHandler {

    @Override
    public Object failure(HttpResponse response, Method method, Object[] args) {
        throw new BadRequestException(StrUtil.format("request error, http status: {}", response.getStatusCode()), response.getStatusCode());
    }
}
