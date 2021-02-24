package cn.graydove.httpmaster.starter.config;

import cn.graydove.httpmaster.starter.config.core.HandlerRegisterBeanPostProcessor;
import cn.graydove.httpmaster.starter.handler.RequestHandlerManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpMasterConfiguration {

    @Bean
    public HandlerRegisterBeanPostProcessor handlerRegisterBeanPostProcessor() {
        return new HandlerRegisterBeanPostProcessor();
    }

    @Bean
    public RequestHandlerManager requestHandlerManager() {
        return new RequestHandlerManager();
    }
}
