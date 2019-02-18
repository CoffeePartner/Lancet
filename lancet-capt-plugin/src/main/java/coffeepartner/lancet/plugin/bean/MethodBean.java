package coffeepartner.lancet.plugin.bean;


import org.objectweb.asm.tree.MethodNode;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MethodBean {

    public static MethodBean create(MethodNode methodNode, String className) {
        MethodBean bean = new MethodBean();
        bean.className = className;
        bean.access = methodNode.access;
        bean.desc = methodNode.desc;
        bean.name = methodNode.name;
        if(methodNode.visibleAnnotations != null) {
            bean.annotations = methodNode.invisibleAnnotations.stream().
        }
    }

    public List<AnnotationBean> annotations = Collections.emptyList();
    public String className;
    public int access;
    public String name;
    public String desc;

    // extra binding info

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodBean that = (MethodBean) o;
        return access == that.access &&
                annotations.equals(that.annotations) &&
                className.equals(that.className) &&
                name.equals(that.name) &&
                desc.equals(that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(annotations, className, access, name, desc);
    }
}
