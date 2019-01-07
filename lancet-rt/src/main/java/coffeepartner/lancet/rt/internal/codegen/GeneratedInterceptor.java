package coffeepartner.lancet.rt.internal.codegen;

import coffeepartner.lancet.rt.AroundContext;
import coffeepartner.lancet.rt.Interceptor;

import javax.annotation.Nullable;

public abstract class GeneratedInterceptor implements Interceptor {

    private AroundContext context;

    @Override
    public final Object intercept(AroundContext context) {
        this.context = context;
        try {
            return intercept(context.getArgs());
        } finally {
            this.context = null;
        }
    }

    final public AroundContext getContext() {
        return context;
    }

    final public void setArg(int index, Object o) {
        context.getArgs()[index] = o;
    }

    @Nullable
    abstract Object intercept(Object[] args);
}
