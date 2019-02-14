package coffeepartner.lancet.rt.annotations;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Within {
    /**
     * Restrict code defined in a type.
     *
     * @return regex to match types
     */
    String value();
}
