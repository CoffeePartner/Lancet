package coffeepartner.lancet.rt.annotations;

import coffeepartner.lancet.rt.Scope;

import java.lang.annotation.*;

/**
 * Limit the target classes.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ImplementedInterface {

    /**
     * Interface array, jvm internal name, $ for inner class.
     * For example : a/b/c$d;
     */
    String[] value();

    /**
     * The scope of interface array.
     */
    int scope() default Scope.SELF;
}
