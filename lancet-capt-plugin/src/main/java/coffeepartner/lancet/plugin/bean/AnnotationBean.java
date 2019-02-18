package coffeepartner.lancet.plugin.bean;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class AnnotationBean {

    public String desc;
    public List<Object> values = Collections.emptyList();

    @Override
    public String toString() {
        return "AnnotationBean{" +
                "desc='" + desc + '\'' +
                ", values=" + values +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnnotationBean that = (AnnotationBean) o;
        return desc.equals(that.desc) &&
                values.equals(that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desc, values);
    }
}
