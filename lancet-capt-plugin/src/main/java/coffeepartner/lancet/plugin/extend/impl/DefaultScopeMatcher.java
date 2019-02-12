package coffeepartner.lancet.plugin.extend.impl;

import coffeepartner.capt.plugin.api.graph.ClassGraph;
import coffeepartner.capt.plugin.api.graph.ClassInfo;
import coffeepartner.lancet.plugin.extend.ScopeMatcher;

import javax.annotation.Nullable;

public class DefaultScopeMatcher implements ScopeMatcher {
    @Nullable
    @Override
    public Boolean satisfied(ClassGraph graph, ClassInfo classInfo, int scope) {
        return null;
    }
}
