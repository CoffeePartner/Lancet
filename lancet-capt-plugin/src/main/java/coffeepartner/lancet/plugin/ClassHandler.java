package coffeepartner.lancet.plugin;

import coffeepartner.capt.plugin.api.graph.ClassInfo;

import javax.annotation.Nullable;

public interface ClassHandler {

    MethodHandler visitMethod();

    boolean visitEnd(MethodHandler factory);

    interface Factory {
        @Nullable
        ClassHandler create(ClassInfo info, boolean required);
    }
}
