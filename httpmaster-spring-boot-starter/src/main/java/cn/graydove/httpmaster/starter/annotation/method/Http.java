package cn.graydove.httpmaster.starter.annotation.method;


import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.starter.enums.HttpBodyStrategy;

import java.lang.annotation.*;

/**
 * @author graydove
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Http {

    HttpMethod method() default HttpMethod.GET;

    String url() default "";

    String path() default "";

    HttpBodyStrategy httpBodyStrategy() default HttpBodyStrategy.AUTO;
}
