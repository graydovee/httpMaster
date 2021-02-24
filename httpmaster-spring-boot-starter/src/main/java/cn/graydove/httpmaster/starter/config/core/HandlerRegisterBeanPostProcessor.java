package cn.graydove.httpmaster.starter.config.core;

import cn.graydove.httpmaster.starter.handler.AfterRequestHandler;
import cn.graydove.httpmaster.starter.handler.BeforeRequestHandler;
import cn.graydove.httpmaster.starter.handler.RequestFailureHandler;
import cn.graydove.httpmaster.starter.handler.RequestHandlerRegister;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class HandlerRegisterBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private RequestHandlerRegister requestHandlerRegister;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeforeRequestHandler) {
            BeforeRequestHandler beforeRequestHandler = (BeforeRequestHandler) bean;
            requestHandlerRegister.registerBeforeRequestHandler(beforeRequestHandler);
        }
        if (bean instanceof AfterRequestHandler) {
            AfterRequestHandler afterRequestHandler = (AfterRequestHandler) bean;
            requestHandlerRegister.registerAfterRequestHandler(afterRequestHandler);
        }
        if (bean instanceof RequestFailureHandler) {
            RequestFailureHandler requestFailureHandler = (RequestFailureHandler) bean;
            requestHandlerRegister.registerRequestFailureHandler(requestFailureHandler);
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        requestHandlerRegister = applicationContext.getBean(RequestHandlerRegister.class);
    }
}
