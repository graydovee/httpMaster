package cn.graydove.httpmaster.starter.handler.support.relover;

import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.hutool.json.JSONUtil;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

@Order(ResolverOrderConstant.BEAN)
public class BeanReturnResolver implements ReturnResolver {

    @Override
    public boolean isSupport(Class<?> returnType) {
        return true;
    }

    @Override
    public Object resolve(HttpResponse httpResponse, Method method) {
        return JSONUtil.toBean(StringContentContext.getStrContent(httpResponse), method.getReturnType());
    }
}
