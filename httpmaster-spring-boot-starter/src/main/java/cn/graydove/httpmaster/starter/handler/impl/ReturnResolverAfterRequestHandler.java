package cn.graydove.httpmaster.starter.handler.impl;

import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.starter.bean.OrderBeanRegister;
import cn.graydove.httpmaster.starter.handler.impl.relover.ReturnResolver;
import cn.hutool.core.util.ClassUtil;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author graydove
 */
@Order(Integer.MAX_VALUE - 10)
public class ReturnResolverAfterRequestHandler extends AbstractAutoCloseAfterRequestHandler {

    private final OrderBeanRegister<ReturnResolver> returnResolverRegister;

    public ReturnResolverAfterRequestHandler(OrderBeanRegister<ReturnResolver> returnResolverRegister) {
        this.returnResolverRegister = returnResolverRegister;
    }

    @Override
    public Object success(HttpResponse response, Method method, Object[] args) {
        Class<?> type = method.getReturnType();
        if (ClassUtil.isAssignable(response.getClass(), type)) {
            return null;
        }
        for (ReturnResolver resolver : returnResolverRegister.getData()) {
            if (resolver.isSupport(type)) {
                Object o = resolver.resolve(response, method);
                if (o != null) {
                    return o;
                }
            }
        }
        return null;
    }
}
