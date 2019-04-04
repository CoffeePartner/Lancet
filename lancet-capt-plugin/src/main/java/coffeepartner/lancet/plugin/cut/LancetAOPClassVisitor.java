package coffeepartner.lancet.plugin.cut;

import coffeepartner.capt.plugin.api.asm.CaptClassVisitor;
import coffeepartner.lancet.plugin.ClassHandler;
import coffeepartner.lancet.plugin.MethodHandler;
import org.objectweb.asm.MethodVisitor;

public class LancetAOPClassVisitor extends CaptClassVisitor {

    private final ClassHandler handler;
    private final boolean isAnnotationClass;

    public LancetAOPClassVisitor(ClassHandler handler, boolean isAnnotationClass) {
        this.handler = handler;
        this.isAnnotationClass = isAnnotationClass;
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        if (isAnnotationClass) {
            cv = context().getLastWriter();
        }
        super.visit(version, access, name, signature, superName, interfaces);
    }

    private MethodHandler createCurrent() {
        return this::visitMethod;
    }

    private MethodHandler createNext() {
        return super::visitMethod;
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return handler.visitMethod(access, name, desc, signature, exceptions, createNext());
    }

    @Override
    public void visitEnd() {
        if (handler.visitEnd(createCurrent())) {
            context().notifyChanged();
        }
        super.visitEnd();
    }
}
