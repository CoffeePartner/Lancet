package coffeepartner.lancet.plugin.extend;

import coffeepartner.capt.plugin.api.graph.ClassGraph;
import coffeepartner.capt.plugin.api.graph.ClassInfo;

import javax.annotation.Nullable;

public interface ScopeMatcher {
    /**
     * @param graph     class graph
     * @param classInfo the class
     * @param scope     the input scope
     * @return null if can not deal with input scope
     */
    @Nullable
    Boolean satisfied(ClassGraph graph, ClassInfo classInfo, int scope);
}
