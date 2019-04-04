package coffeepartner.lancet.plugin;

import coffeepartner.capt.plugin.api.graph.ClassInfo;
import org.objectweb.asm.MethodVisitor;

import javax.annotation.Nullable;

public interface ClassHandler {

    MethodVisitor visitMethod(int access, String name, String desc,
                              String signature, String[] exceptions, MethodHandler handler);

    boolean visitEnd(MethodHandler factory);

    interface Factory {
        @Nullable
        ClassHandler create(ClassInfo info, boolean required);
    }
}
