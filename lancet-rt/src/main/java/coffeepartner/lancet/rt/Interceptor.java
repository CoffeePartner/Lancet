package coffeepartner.lancet.rt;

import javax.annotation.Nullable;

public interface Interceptor {

    @Nullable
    Object intercept(AroundContext context);
}
