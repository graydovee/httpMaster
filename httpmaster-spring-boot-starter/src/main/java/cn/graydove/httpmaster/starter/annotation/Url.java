package cn.graydove.httpmaster.starter.annotation;

import java.lang.annotation.*;

/**
 * @author graydove
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Url {

}
