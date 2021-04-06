package cn.graydove.httpmaster.starter.config.support;

import cn.graydove.httpmaster.core.engine.HttpEngine;
import cn.graydove.httpmaster.core.engine.support.okhttp.DefaultOkHttpClientFactory;
import cn.graydove.httpmaster.core.engine.support.okhttp.OkHttpClientFactory;
import cn.graydove.httpmaster.core.engine.support.okhttp.OkHttpEngine;
import cn.graydove.httpmaster.starter.config.ConfigConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author graydove
 */
@Configuration
@ConditionalOnProperty(prefix = ConfigConstant.PROPERTIES_PREFIX, name = "engine", havingValue = ConfigConstant.Engine.OK_HTTP)
public class OkHttpConfiguration {

    @Bean
    @ConditionalOnMissingBean(OkHttpClientFactory.class)
    public OkHttpClientFactory httpClientFactory() {
        return new DefaultOkHttpClientFactory();
    }

    @Bean
    @ConditionalOnMissingBean(HttpEngine.class)
    public HttpEngine httpEngine(OkHttpClientFactory httpClientFactory) {
        return new OkHttpEngine(httpClientFactory);
    }
}
