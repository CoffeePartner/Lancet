package coffeepartner.lancet.plugin.extend.impl;

import coffeepartner.capt.plugin.api.graph.ClassGraph;
import coffeepartner.capt.plugin.api.graph.ClassInfo;
import coffeepartner.lancet.plugin.extend.ScopeMatcher;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("unchecked")
public class ChainedScopeMatcher implements ScopeMatcher {

    private final List<ScopeMatcher> matchers;
    private Function[] functions = new Function[Integer.SIZE];

    public ChainedScopeMatcher(List<ScopeMatcher> matchers) {
        this.matchers = matchers;
    }

    @Nullable
    @Override
    public Function<ClassInfo, Stream<? extends ClassInfo>> match(ClassGraph graph, int scope) {
        if (scope <= 0) {
            throw new IllegalArgumentException("Scope " + scope + " is not more than 0");
        }
        return s -> {
            Stream.Builder<Stream<? extends ClassInfo>> builder = Stream.builder();
            for (int i = 0; i < 31; i++) {
                int oneBitScope = 1 << i;
                if ((oneBitScope & scope) != 0) {
                    builder.accept(queryOneBitMatcher(graph, oneBitScope, i).apply(s));
                } else if (oneBitScope > scope) {
                    break;
                }
            }
            return builder.build().flatMap(Function.identity()).distinct(); // distinct to filter same info
        };
    }

    private Function<ClassInfo, Stream<? extends ClassInfo>> queryOneBitMatcher(ClassGraph graph, int scope, int bit) {
        Function<ClassInfo, Stream<? extends ClassInfo>> function = functions[bit];
        if (function == null) {
            function = generateOneBitMatcher(graph, scope);
            functions[bit] = function;
        }
        return function;
    }

    private Function<ClassInfo, Stream<? extends ClassInfo>> generateOneBitMatcher(ClassGraph graph, int scope) {
        Optional<Function<ClassInfo, Stream<? extends ClassInfo>>> s = matchers.stream()
                .map(m -> m.match(graph, scope))
                .filter(Objects::nonNull)
                .findFirst();
        if (!s.isPresent()) {
            throw new IllegalArgumentException("Unsupported scope: 0x" + Integer.toHexString(scope));
        }
        return s.get();
    }
}
