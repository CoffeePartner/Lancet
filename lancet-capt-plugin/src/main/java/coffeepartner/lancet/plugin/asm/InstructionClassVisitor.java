package coffeepartner.lancet.plugin.asm;

import coffeepartner.capt.plugin.api.asm.CaptClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class InstructionClassVisitor extends CaptClassVisitor {


    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return super.visitMethod(access, name, desc, signature, exceptions);
    }
}
