package coffeepartner.lancet.plugin;


import org.objectweb.asm.MethodVisitor;

public interface MethodHandler {

    MethodVisitor create(int access, String name, String desc,
                         String signature, String[] exceptions, MethodHandler next);

}
