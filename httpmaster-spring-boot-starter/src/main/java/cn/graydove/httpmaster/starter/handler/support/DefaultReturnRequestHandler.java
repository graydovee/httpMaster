package cn.graydove.httpmaster.starter.handler.support;

import cn.graydove.httpmaster.core.exception.HttpRequestException;
import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.core.response.support.StringHttpContent;
import cn.graydove.httpmaster.starter.exception.BadRequestException;
import cn.graydove.httpmaster.starter.handler.AfterRequestHandler;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONUtil;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

@Order
public class DefaultReturnRequestHandler extends AbstractAutoCloseRequestHandler {
    @Override
    public Object doHandle(HttpResponse response, Method method, Object[] args) {
        if (HttpStatus.HTTP_OK != response.getStatusCode()) {
            throw new BadRequestException(StrUtil.format("request error, http status: {}", response.getStatusCode()), response.getStatusCode());
        }

        Class<?> type = method.getReturnType();
        if (byte[].class.equals(type)) {
            return response.getHttpContent().getContentBytes();
        }
        String contentStr = new StringHttpContent(response.getHttpContent()).getContentStr();
        if (String.class.equals(type)) {
            return contentStr;
        } else {
            return JSONUtil.toBean(contentStr, type);
        }
    }
}
