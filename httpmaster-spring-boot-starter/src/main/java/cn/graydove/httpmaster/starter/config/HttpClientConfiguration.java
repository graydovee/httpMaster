package cn.graydove.httpmaster.starter.config;

import cn.graydove.httpmaster.core.engine.HttpEngine;
import cn.graydove.httpmaster.core.engine.support.httpclient.DefaultHttpClientFactory;
import cn.graydove.httpmaster.core.engine.support.httpclient.HttpClientEngine;
import cn.graydove.httpmaster.core.engine.support.httpclient.HttpClientFactory;
import cn.graydove.httpmaster.core.request.HttpRequestFactory;
import cn.graydove.httpmaster.core.request.support.DefaultHttpRequestFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpClientConfiguration {

    @Bean
    @ConditionalOnMissingBean(HttpClientFactory.class)
    public HttpClientFactory httpClientFactory() {
        return new DefaultHttpClientFactory();
    }

    @Bean
    @ConditionalOnMissingBean(HttpRequestFactory.class)
    public HttpRequestFactory httpRequestFactory() {
        return new DefaultHttpRequestFactory();
    }

    @Bean
    @ConditionalOnMissingBean(HttpEngine.class)
    public HttpEngine httpEngine(HttpRequestFactory httpRequestFactory, HttpClientFactory httpClientFactory) {
        return new HttpClientEngine(httpRequestFactory, httpClientFactory);
    }

}
