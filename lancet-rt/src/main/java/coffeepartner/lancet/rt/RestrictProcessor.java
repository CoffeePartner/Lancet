package coffeepartner.lancet.rt;

import coffeepartner.lancet.rt.compiletime.AnnotationContext;
import coffeepartner.lancet.rt.compiletime.CodeApperance;
import coffeepartner.lancet.rt.internal.annotations.AutoRemovedAfterCompile;


@AutoRemovedAfterCompile
public interface RestrictProcessor {

    boolean accept(CodeApperance code, AnnotationContext ctx);
}
