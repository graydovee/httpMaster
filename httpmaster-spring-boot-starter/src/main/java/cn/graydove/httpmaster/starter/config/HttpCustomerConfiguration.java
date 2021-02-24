package cn.graydove.httpmaster.starter.config;

import cn.graydove.httpmaster.starter.handler.support.DefaultReturnRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpCustomerConfiguration {

    @Bean
    public DefaultReturnRequestHandler stringAfterRequestHandler() {
        return new DefaultReturnRequestHandler();
    }
}
