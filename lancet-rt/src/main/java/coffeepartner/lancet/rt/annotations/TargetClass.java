package coffeepartner.lancet.rt.annotations;

import coffeepartner.lancet.rt.Scope;

/**
 * Limit the target classes.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface TargetClass {
    String value();

    Scope scope() default Scope.SELF;
}
