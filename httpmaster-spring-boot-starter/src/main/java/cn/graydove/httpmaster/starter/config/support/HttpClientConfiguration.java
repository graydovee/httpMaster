package cn.graydove.httpmaster.starter.config.support;

import cn.graydove.httpmaster.core.engine.HttpEngine;
import cn.graydove.httpmaster.core.engine.support.httpclient.DefaultHttpClientFactory;
import cn.graydove.httpmaster.core.engine.support.httpclient.HttpClientEngine;
import cn.graydove.httpmaster.core.engine.support.httpclient.HttpClientFactory;
import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.core.request.support.DefaultHttpRequestFactory;
import cn.graydove.httpmaster.starter.config.ConfigConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author graydove
 */
@Configuration
@ConditionalOnProperty(prefix = ConfigConstant.PROPERTIES_PREFIX, name = "engine", havingValue = ConfigConstant.Engine.HTTP_CLIENT)
public class HttpClientConfiguration  {

    @Bean
    @ConditionalOnMissingBean(HttpClientFactory.class)
    public HttpClientFactory httpClientFactory() {
        return new DefaultHttpClientFactory();
    }

    @Bean
    @ConditionalOnMissingBean(HttpEngine.class)
    public HttpEngine httpEngine(HttpClientFactory httpClientFactory) {
        return new HttpClientEngine(httpClientFactory);
    }

}
