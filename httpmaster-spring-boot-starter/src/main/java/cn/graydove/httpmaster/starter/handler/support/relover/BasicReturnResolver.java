package cn.graydove.httpmaster.starter.handler.support.relover;

import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ClassUtil;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

@Order(ResolverOrderConstant.BASIC)
public class BasicReturnResolver implements ReturnResolver {

    @Override
    public boolean isSupport(Class<?> returnType) {
        return ClassUtil.isBasicType(returnType);
    }

    @Override
    public Object resolve(HttpResponse httpResponse, Method method) {
        return Convert.convert(method.getReturnType(), StringContentContext.getStrContent(httpResponse));
    }
}
