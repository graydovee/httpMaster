package cn.graydove.httpmaster.starter.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Component
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpService {

    String url() default "";
}
