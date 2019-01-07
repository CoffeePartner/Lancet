package coffeepartner.lancet.rt;

public interface Interceptor {

    Object intercept(AroundContext context);
}
