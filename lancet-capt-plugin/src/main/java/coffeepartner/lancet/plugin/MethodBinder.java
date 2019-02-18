package coffeepartner.lancet.plugin;

import java.util.List;
import java.util.Objects;

public class MethodBinder {

    public String className;
    public int access;
    public String name;
    public String desc;

    List<String> bindingClasses;

    List<MethodBinder> bindingMethods;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MethodBinder that = (MethodBinder) o;
        return access == that.access &&
                className.equals(that.className) &&
                name.equals(that.name) &&
                desc.equals(that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, access, name, desc);
    }
}
