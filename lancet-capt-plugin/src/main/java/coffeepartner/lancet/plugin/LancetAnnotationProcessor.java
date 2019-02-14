package coffeepartner.lancet.plugin;

import coffeepartner.capt.plugin.api.graph.ClassInfo;
import coffeepartner.capt.plugin.api.process.AnnotationProcessor;
import coffeepartner.capt.plugin.api.process.ClassConsumer;

import javax.annotation.Nullable;

public class LancetAnnotationProcessor extends AnnotationProcessor {

    @Nullable
    @Override
    public ClassConsumer onAnnotationMismatch(ClassInfo info) {
        onAnnotationClassRemoved(info);
        return null;
    }

    @Nullable
    @Override
    public ClassConsumer onAnnotationMatched(ClassInfo info) {
        return onAnnotationClassAdded(info);
    }

    @Nullable
    @Override
    public ClassConsumer onAnnotationChanged(ClassInfo info) {
        // TODO still match, need more info
        return;
    }

    @Override
    public void onAnnotationClassRemoved(ClassInfo info) {
        // TODO remove annotation
    }

    @Nullable
    @Override
    public ClassConsumer onAnnotationClassAdded(ClassInfo info) {
        // TODO add annotation
        return;
    }

    @Nullable
    @Override
    public ClassConsumer onAnnotationClassNotChanged(ClassInfo info) {
        return onAnnotationClassAdded(info);
    }

    @Override
    public void onProcessEnd() {
        // TODO on end
    }
}