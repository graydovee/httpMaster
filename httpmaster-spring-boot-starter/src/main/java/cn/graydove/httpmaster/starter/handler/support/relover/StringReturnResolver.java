package cn.graydove.httpmaster.starter.handler.support.relover;

import cn.graydove.httpmaster.core.response.HttpResponse;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

@Order(ResolverOrderConstant.STRING)
public class StringReturnResolver extends SpecialTypeReturnResolver {

    @Override
    protected Class<?>[] supportClass() {
        return new Class[]{String.class};
    }

    @Override
    public Object resolve(HttpResponse httpResponse, Method method) {
        return StringContentContext.getStrContent(httpResponse);
    }
}
