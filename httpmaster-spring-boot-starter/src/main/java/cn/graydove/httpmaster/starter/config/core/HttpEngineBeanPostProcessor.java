package cn.graydove.httpmaster.starter.config.core;

import cn.graydove.httpmaster.core.engine.HttpEngine;
import cn.graydove.httpmaster.core.engine.RetryHttpEngine;
import cn.graydove.httpmaster.starter.handler.RequestContext;
import cn.graydove.httpmaster.starter.handler.RequestContextRegister;
import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author graydove
 */
@Component
public class HttpEngineBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Value("${http-master.engine.retry.enable:false}")
    private Boolean enableRetry;

    @Value("${http-master.engine.retry.times:3}")
    private Integer retryTimes;

    @Value("${http-master.engine.retry.interval:1000}")
    private Integer retryInterval;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (Boolean.TRUE.equals(enableRetry) && bean instanceof HttpEngine) {
            HttpEngine httpEngine = (HttpEngine) bean;
            if (null != retryTimes && null != retryInterval) {
                httpEngine = new RetryHttpEngine(httpEngine, retryTimes, retryInterval);
            } else {
                httpEngine = new RetryHttpEngine(httpEngine);
            }
            RequestContextRegister register = applicationContext.getBean(RequestContextRegister.class);
            register.setHttpEngine(httpEngine);
            return httpEngine;
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
