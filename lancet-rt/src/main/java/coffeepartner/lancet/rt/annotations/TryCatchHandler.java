package coffeepartner.lancet.rt.annotations;

import java.lang.annotation.*;

/**
 * Pre process the classes who extends {@link Throwable}.
 * The method desc should like (A)A (A is a class that extends Throwable).
 * Combine with {@link Within} to restrict the scope.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface TryCatchHandler {

    /**
     * @return the throwable type regex
     */
    String value() default "java/lang/Throwable";

    /**
     * @return priority
     */
    int priority() default 0;
}
