package coffeepartner.lancet.plugin.extend.impl;

import coffeepartner.capt.plugin.api.graph.ClassGraph;
import coffeepartner.capt.plugin.api.graph.ClassInfo;
import coffeepartner.lancet.plugin.extend.ScopeMatcher;

import javax.annotation.Nullable;
import java.util.List;

public class ChainedScopeMatcher implements ScopeMatcher {

    private final List<ScopeMatcher> matchers;

    public ChainedScopeMatcher(List<ScopeMatcher> matchers) {
        this.matchers = matchers;
    }

    @Nullable
    @Override
    public Boolean satisfied(ClassGraph graph, ClassInfo classInfo, int scope) {
        return null;
    }
}
