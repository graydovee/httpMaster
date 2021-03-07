package cn.graydove.httpmaster.starter.handler.support;

import cn.graydove.httpmaster.core.response.HttpResponse;
import cn.graydove.httpmaster.starter.bean.OrderBeanRegister;
import cn.graydove.httpmaster.starter.handler.support.relover.ReturnResolver;
import cn.graydove.httpmaster.starter.handler.support.relover.StringContentContext;
import cn.hutool.core.util.ClassUtil;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

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
        try {
            for (ReturnResolver resolver : returnResolverRegister.getData()) {
                if (resolver.isSupport(type)) {
                    Object o = resolver.resolve(response, method);
                    if (o != null) {
                        return o;
                    }
                }
            }
        } finally {
            StringContentContext.clear();
        }
        return null;
    }
}
