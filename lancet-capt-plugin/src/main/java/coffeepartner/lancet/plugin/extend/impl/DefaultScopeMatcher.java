package coffeepartner.lancet.plugin.extend.impl;

import coffeepartner.capt.plugin.api.graph.ClassGraph;
import coffeepartner.capt.plugin.api.graph.ClassInfo;
import coffeepartner.lancet.plugin.extend.ScopeMatcher;
import org.objectweb.asm.Opcodes;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.stream.Stream;

import static coffeepartner.lancet.rt.Scope.*;

public class DefaultScopeMatcher implements ScopeMatcher {

    @Nullable
    @Override
    public Function<ClassInfo, Stream<? extends ClassInfo>> match(ClassGraph graph, int scope) {
        switch (scope) {
            case SELF:
            case DIRECT:
            case LEAF:
            case ALL_CHILDREN:
                return s -> visit(s, scope);
        }
        return null;
    }

    private Stream<? extends ClassInfo> visit(ClassInfo root, int scope) {
        if ((root.access() & Opcodes.ACC_INTERFACE) != 0) {
            return visitInterfaces(root, scope);
        }
        return visitClass(root, scope);
    }

    private Stream<? extends ClassInfo> visitClass(ClassInfo root, int scope) {
        Stream.Builder<Stream<? extends ClassInfo>> builder = Stream.builder();
        switch (scope) {
            case SELF:
                builder.accept(Stream.of(root));
                break;
            case ALL_CHILDREN:
            case LEAF:
                builder.accept(root.classChildren().stream()
                        .flatMap(s -> visitClass(s, ALL_CHILDREN)));
            case DIRECT:
                builder.accept(root.classChildren().stream());
                break;
        }
        Stream<? extends ClassInfo> res = builder.build().flatMap(Function.identity());
        if (scope == LEAF) {
            res = res.filter(s -> s.classChildren().isEmpty());
        }
        return res;
    }

    private Stream<? extends ClassInfo> visitInterfaces(ClassInfo root, int scope) {
        Stream.Builder<Stream<? extends ClassInfo>> builder = Stream.builder();
        switch (scope) {
            case LEAF:
            case ALL_CHILDREN:
                builder.accept(root.implementedClasses().stream().flatMap(s -> visitClass(s, scope)));
            case DIRECT:
                builder.accept(root.interfaceChildren().stream().flatMap(i -> visitInterfaces(i, scope)));
            case SELF:
                builder.accept(root.implementedClasses().stream());
                break;
        }
        return builder.build().flatMap(Function.identity());
    }
}
