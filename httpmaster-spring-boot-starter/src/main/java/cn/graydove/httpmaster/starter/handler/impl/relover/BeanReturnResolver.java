package cn.graydove.httpmaster.starter.handler.impl.relover;

import cn.graydove.httpmaster.core.response.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author graydove
 */
@Order(ResolverOrderConstant.BEAN)
public class BeanReturnResolver implements ReturnResolver {

    private static Logger logger = LoggerFactory.getLogger(BeanReturnResolver.class);

    private ObjectMapper objectMapper;

    public BeanReturnResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean isSupport(Class<?> returnType) {
        return true;
    }

    @Override
    public Object resolve(HttpResponse httpResponse, Method method) {
        String json = httpResponse.getHttpContent().getContentStr();
        Type type = method.getGenericReturnType();
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructType(type));
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
