package coffeepartner.lancet.rt.annotations;

import java.lang.annotation.*;

/**
 * Indicate the hook method.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Insert {

    /**
     * The target method name.
     */
    String value();

    /**
     * if true, create empty method which only invoke super if not exits
     */
    boolean mayCreateSuper() default false;

    /**
     * Priority to sort hook methods, the smaller, the higher.
     */
    int priority() default 0;
}
