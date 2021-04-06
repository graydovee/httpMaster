package cn.graydove.httpmaster.starter.config;


import cn.graydove.httpmaster.starter.config.core.HttpRequestBeanDefinitionRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author graydove
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(HttpRequestBeanDefinitionRegister.class)
public @interface EnableHttpMaster {
}
