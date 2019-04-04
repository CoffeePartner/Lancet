package coffeepartner.lancet.plugin;


import org.objectweb.asm.MethodVisitor;

import javax.annotation.Nullable;

public interface MethodHandler {

    MethodVisitor create(int access, String name, String desc,
                         String signature, String[] exceptions);

}
