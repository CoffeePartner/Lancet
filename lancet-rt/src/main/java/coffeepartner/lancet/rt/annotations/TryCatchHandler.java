package coffeepartner.lancet.rt.annotations;

import java.lang.annotation.*;

/**
 * Pre process the classes who extends {@link Throwable}.
 * The method desc should like (A)A (A is a class that extends Throwable).
 * Combine with {@link NameRegex} to restrict the scope.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface TryCatchHandler {

    /**
     * @return priority
     */
    int priority() default 0;
}