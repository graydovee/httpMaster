package cn.graydove.httpmaster.starter.config;

import cn.graydove.httpmaster.starter.bean.OrderBeanRegister;
import cn.graydove.httpmaster.starter.handler.support.*;
import cn.graydove.httpmaster.starter.handler.support.filter.DefaultRequestFilter;
import cn.graydove.httpmaster.starter.handler.support.filter.RequestFilter;
import cn.graydove.httpmaster.starter.handler.support.relover.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class RequestHandlerConfiguration {

    @Bean
    @ConditionalOnMissingBean(RequestFilter.class)
    public DefaultRequestFilter defaultRequestFilter() {
        return new DefaultRequestFilter();
    }

    @Bean
    public OrderBeanRegister<RequestFilter> requestFilterRegister(List<RequestFilter> requestFilterList) {
        return new OrderBeanRegister<>(requestFilterList);
    }

    @Bean
    public RequestFilterBeforeHandler globalConfigureBeforeRequestHandler(OrderBeanRegister<RequestFilter> requestFilterRegister) {
        return new RequestFilterBeforeHandler(requestFilterRegister);
    }

    @Bean
    public BasicReturnResolver basicReturnResolver() {
        return new BasicReturnResolver();
    }

    @Bean
    public BeanReturnResolver beanReturnResolver() {
        return new BeanReturnResolver();
    }

    @Bean
    public ByteArrayReturnResolver byteArrayReturnResolver() {
        return new ByteArrayReturnResolver();
    }

    @Bean
    public StringReturnResolver stringReturnResolver() {
        return new StringReturnResolver();
    }

    @Bean
    public OrderBeanRegister<ReturnResolver> returnResolverRegister(List<ReturnResolver> returnResolverList) {
        return new OrderBeanRegister<>(returnResolverList);
    }

    @Bean
    public ReturnResolverAfterRequestHandler returnResolverAfterRequestHandler(OrderBeanRegister<ReturnResolver> returnResolverRegister) {
        return new ReturnResolverAfterRequestHandler(returnResolverRegister);
    }

    @Bean
    public InputStreamAfterRequestHandler inputStreamAfterRequestHandler() {
        return new InputStreamAfterRequestHandler();
    }

    @Bean
    public ExceptionAfterRequestHandler exceptionAfterRequestHandler() {
        return new ExceptionAfterRequestHandler();
    }

    @Bean
    public ExceptionRequestFailureHandler exceptionRequestFailureHandler() {
        return new ExceptionRequestFailureHandler();
    }

}
