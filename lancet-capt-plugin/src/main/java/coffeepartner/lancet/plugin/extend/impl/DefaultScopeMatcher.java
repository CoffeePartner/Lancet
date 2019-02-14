package coffeepartner.lancet.plugin.extend.impl;

import coffeepartner.capt.plugin.api.graph.ClassGraph;
import coffeepartner.capt.plugin.api.graph.ClassInfo;
import coffeepartner.lancet.plugin.extend.ScopeMatcher;

import javax.annotation.Nullable;
import java.util.Set;
import java.util.function.Function;

public class DefaultScopeMatcher implements ScopeMatcher {

    @Nullable
    @Override
    public Function<ClassInfo, Set<ClassInfo>> match(ClassGraph graph, int scope) {
        return null;
    }
}
