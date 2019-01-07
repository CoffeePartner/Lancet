package coffeepartner.lancet.rt.annotations;

import coffeepartner.lancet.rt.Lancet;
import coffeepartner.lancet.rt.internal.annotations.AutoReplaced;

/**
 * Annotate a method to make it interceptable by {@link Lancet#getGlobalInterceptor()}.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@AutoReplaced
public @interface Interceptable {
}