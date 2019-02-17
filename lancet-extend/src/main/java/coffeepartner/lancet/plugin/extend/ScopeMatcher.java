package coffeepartner.lancet.plugin.extend;

import coffeepartner.capt.plugin.api.graph.ClassGraph;
import coffeepartner.capt.plugin.api.graph.ClassInfo;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.stream.Stream;

public interface ScopeMatcher {

    /**
     * @param scope the input scope, multiple bits will divide into one bit each time
     * @return null if can not deal with input scope
     */
    @Nullable
    Function<ClassInfo, Stream<? extends ClassInfo>> match(ClassGraph graph, int scope);
}
