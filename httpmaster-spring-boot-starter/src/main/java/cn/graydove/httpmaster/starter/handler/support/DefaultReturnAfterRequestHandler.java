package cn.graydove.httpmaster.starter.handler.support;

import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.core.response.support.StringHttpContent;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

@Order
public class DefaultReturnAfterRequestHandler extends AbstractAutoCloseAfterRequestHandler {

    @Override
    public Object success(HttpResponse response, Method method, Object[] args) {

        Class<?> type = method.getReturnType();
        if (ClassUtil.isAssignable(HttpResponse.class, type)) {
            return response;
        }
        if (byte[].class.equals(type)) {
            return response.getHttpContent().getContentBytes();
        }
        String contentStr = new StringHttpContent(response.getHttpContent()).getContentStr();
        if (String.class.equals(type)) {
            return contentStr;
        } else if (ClassUtil.isBasicType(type)) {
            return Convert.convert(type, contentStr);
        } else {
            return JSONUtil.toBean(contentStr, type);
        }
    }
}
