package coffeepartner.lancet.plugin;

import coffeepartner.capt.plugin.api.CaptInternal;
import coffeepartner.capt.plugin.api.Plugin;
import coffeepartner.capt.plugin.api.annotations.Def;
import coffeepartner.capt.plugin.api.process.AnnotationProcessor;
import coffeepartner.capt.plugin.api.transform.ClassTransformer;
import coffeepartner.lancet.plugin.extend.ScopeMatcher;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Def
public class LancetPlugin extends Plugin<CaptInternal> {
    CaptInternal capt;

    @Override
    public void onCreate(CaptInternal capt) throws IOException, InterruptedException {
        this.capt = capt;
        List<ScopeMatcher> matcher = loadMatchers();
    }

    @Nullable
    @Override
    public AnnotationProcessor onProcessAnnotations() {
        return super.onProcessAnnotations();
    }

    @Nullable
    @Override
    public ClassTransformer onTransformClass() {
        return super.onTransformClass();
    }

    private List<ScopeMatcher> loadMatchers() {
        List<ScopeMatcher> matchers = new ArrayList<>();
        Object o = args.arguments().get("extraScopeMatchers");
        if (o != null) {
            Arrays.stream((String[]) o)
                    .map()
        }
        capt.captLoader().loadClass();
    }
}
