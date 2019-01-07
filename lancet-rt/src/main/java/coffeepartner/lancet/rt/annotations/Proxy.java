package coffeepartner.lancet.rt.annotations;

import java.lang.annotation.*;

/**
 * Indicate the hook method.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Proxy {
    /**
     * The target method name.
     */
    String value();

    /**
     * Priority to sort hook methods, the smaller, the higher.
     */
    int priority() default 0;
}
