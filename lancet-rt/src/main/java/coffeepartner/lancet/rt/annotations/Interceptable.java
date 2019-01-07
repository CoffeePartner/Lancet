package coffeepartner.lancet.rt.annotations;

import coffeepartner.lancet.rt.Lancet;

import java.lang.annotation.*;

/**
 * Annotate a method to make it interceptable by {@link Lancet#getGlobalInterceptor()}.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Interceptable {
}