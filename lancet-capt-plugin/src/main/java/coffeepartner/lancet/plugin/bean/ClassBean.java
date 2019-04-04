package coffeepartner.lancet.plugin.bean;

import org.objectweb.asm.tree.ClassNode;

import java.util.List;
import java.util.stream.Collectors;

public class ClassBean {

    public String className;
    public List<MethodBean> methods;

    public static ClassBean create(ClassNode node) {
        ClassBean bean = new ClassBean();
        bean.className = node.name;
        bean.methods = node.methods.stream().map(m -> MethodBean.create(m, node.name)).collect(Collectors.toList());
        return bean;
    }
}
