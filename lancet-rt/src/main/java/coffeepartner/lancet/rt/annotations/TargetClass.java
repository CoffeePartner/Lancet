package coffeepartner.lancet.rt.annotations;

import coffeepartner.lancet.rt.Scope;

import java.lang.annotation.*;

/**
 * Limit the target classes.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TargetClass {
    String value();

    int scope() default Scope.SELF;
}
