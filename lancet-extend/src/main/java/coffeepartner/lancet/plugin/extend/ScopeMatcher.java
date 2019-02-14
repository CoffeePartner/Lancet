package coffeepartner.lancet.plugin.extend;

import coffeepartner.capt.plugin.api.graph.ClassGraph;
import coffeepartner.capt.plugin.api.graph.ClassInfo;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.Function;

public interface ScopeMatcher {
    /**
     * @param scope the input scope
     * @return null if can not deal with input scope
     */
    @Nullable
    Function<ClassInfo, Set<ClassInfo>> match(ClassGraph graph, int scope);
}
