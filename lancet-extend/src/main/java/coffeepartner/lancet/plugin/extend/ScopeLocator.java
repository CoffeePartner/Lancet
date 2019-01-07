package coffeepartner.lancet.plugin.extend;

import android.support.annotation.IntDef;
import coffeepartner.capt.plugin.api.graph.ClassGraph;
import coffeepartner.capt.plugin.api.graph.ClassInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

public interface ScopeLocator {

    int TYPE_CLASS = 0;
    int TYPE_INTERFACE = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef(value = {TYPE_CLASS, TYPE_INTERFACE})
    @interface Type {
    }

    Set<String> locate(ClassGraph graph, ClassInfo classInfo, int scope, @Type int type);
}
