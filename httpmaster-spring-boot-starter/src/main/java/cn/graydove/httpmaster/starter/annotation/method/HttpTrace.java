package cn.graydove.httpmaster.starter.annotation.method;

import cn.graydove.httpmaster.core.enums.HttpMethod;
import cn.graydove.httpmaster.starter.enums.HttpBodyStrategy;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Http(method = HttpMethod.TRACE)
@Documented
public @interface HttpTrace {

    @AliasFor(annotation = Http.class)
    String url() default "";

    @AliasFor(annotation = Http.class)
    String path() default "";

    @AliasFor(annotation = Http.class)
    HttpBodyStrategy httpBodyStrategy() default HttpBodyStrategy.NONE;
}
