package cn.graydove.httpmaster.starter.config;

import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.core.request.support.DefaultHttpRequestFactory;
import cn.graydove.httpmaster.starter.config.core.HandlerRegisterBeanPostProcessor;
import cn.graydove.httpmaster.starter.handler.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author graydove
 */
@Configuration
public class HttpMasterConfiguration {

    @Bean
    public HandlerRegisterBeanPostProcessor handlerRegisterBeanPostProcessor() {
        return new HandlerRegisterBeanPostProcessor();
    }

    @Bean
    public RequestHandlerManager requestHandlerManager(
            List<BeforeRequestHandler> beforeRequestHandlerList,
            List<AfterRequestHandler> afterRequestHandlerList,
            List<RequestFailureHandler> requestFailureHandlerList) {
        return new RequestHandlerManager(beforeRequestHandlerList, afterRequestHandlerList, requestFailureHandlerList);
    }


    @Bean
    @ConditionalOnMissingBean(HttpRequestFactory.class)
    public HttpRequestFactory httpRequestFactory() {
        return new DefaultHttpRequestFactory();
    }
}
