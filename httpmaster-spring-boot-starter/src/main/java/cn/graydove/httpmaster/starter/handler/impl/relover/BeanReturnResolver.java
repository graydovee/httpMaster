package cn.graydove.httpmaster.starter.handler.impl.relover;

import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.hutool.json.JSONUtil;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author graydove
 */
@Order(ResolverOrderConstant.BEAN)
public class BeanReturnResolver implements ReturnResolver {

    @Override
    public boolean isSupport(Class<?> returnType) {
        return true;
    }

    @Override
    public Object resolve(HttpResponse httpResponse, Method method) {
        return JSONUtil.toBean(httpResponse.getHttpContent().getContentStr(), method.getReturnType());
    }
}
