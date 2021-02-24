package cn.graydove.httpmaster.starter.annotation;


import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Body {

    String value() default "";
}