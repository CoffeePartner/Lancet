package coffeepartner.lancet.rt.internal.codegen;

import coffeepartner.lancet.rt.AroundContext;
import coffeepartner.lancet.rt.Interceptor;
import coffeepartner.lancet.rt.Lancet;
import coffeepartner.lancet.rt.annotations.TryCatchHandler;
import coffeepartner.lancet.rt.internal.AroundMethodChain;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodegenHelper {

    /**
     * Invoked by generated code at hook point.
     *
     * @param interceptorArray sorted by priority
     */
    public static void doAround(@Nullable Object target, @Nullable Object thiz, Object[] args, GeneratedInterceptor[] interceptorArray) {

        List<Interceptor> interceptors = new ArrayList<>(interceptorArray.length + 1);

        Interceptor global = Lancet.instance().getGlobalInterceptor();
        if (global != null) {
            interceptors.add(global);
        }
        interceptors.addAll(Arrays.asList(interceptorArray));
        // Optimize for first invoke, reduce clone args once
        new AroundMethodChain(target, thiz, 0, interceptors, null).proceed(args);
    }

    /**
     * For {@link TryCatchHandler}.
     */
    public static Object onThrow(@Nullable Object target, Object[] args, GeneratedInterceptor[] interceptors) {
        return new AroundMethodChain(target, null, 0, Arrays.<Interceptor>asList(interceptors), null).proceed(args);
    }

    /**
     * Lancet.getContext() will redirect to this method.
     */
    public static AroundContext getContext(GeneratedInterceptor interceptor) {
        return interceptor.getContext();
    }
}
