package cn.graydove.httpmaster.starter.handler.impl.relover;

import cn.graydove.httpmaster.core.response.HttpResponse;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * @author graydove
 */
@Order(ResolverOrderConstant.STRING)
public class StringReturnResolver extends SpecialTypeReturnResolver {

    @Override
    protected Class<?>[] supportClass() {
        return new Class[]{String.class};
    }

    @Override
    public Object resolve(HttpResponse httpResponse, Method method) {
        return httpResponse.getHttpContent().getContentStr();
    }
}
