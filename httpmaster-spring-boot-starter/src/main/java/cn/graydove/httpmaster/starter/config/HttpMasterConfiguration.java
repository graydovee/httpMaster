package cn.graydove.httpmaster.starter.config;

import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.core.request.support.DefaultHttpRequestFactory;
import cn.graydove.httpmaster.starter.config.core.HttpEngineBeanPostProcessor;
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
    public HttpEngineBeanPostProcessor handlerRegisterBeanPostProcessor() {
        return new HttpEngineBeanPostProcessor();
    }

    @Bean
    public RequestManager requestHandlerManager(
            List<BeforeRequestHandler> beforeRequestHandlerList,
            List<AfterRequestHandler> afterRequestHandlerList,
            List<RequestFailureHandler> requestFailureHandlerList) {
        return new RequestManager(beforeRequestHandlerList, afterRequestHandlerList, requestFailureHandlerList);
    }


    @Bean
    @ConditionalOnMissingBean(HttpRequestFactory.class)
    public HttpRequestFactory httpRequestFactory() {
        return new DefaultHttpRequestFactory();
    }
}
