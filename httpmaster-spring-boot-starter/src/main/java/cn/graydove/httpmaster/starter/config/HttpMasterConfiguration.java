package cn.graydove.httpmaster.starter.config;

import cn.graydove.httpmaster.starter.config.core.HandlerRegisterBeanPostProcessor;
import cn.graydove.httpmaster.starter.handler.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.List;

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
}
