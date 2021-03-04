package cn.graydove.httpmaster.starter.config;

import cn.graydove.httpmaster.starter.handler.support.DefaultReturnAfterRequestHandler;
import cn.graydove.httpmaster.starter.handler.support.ExceptionAfterRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpCustomerConfiguration {

    @Bean
    public DefaultReturnAfterRequestHandler stringAfterRequestHandler() {
        return new DefaultReturnAfterRequestHandler();
    }

    @Bean
    public ExceptionAfterRequestHandler exceptionAfterRequestHandler() {
        return new ExceptionAfterRequestHandler();
    }
}
