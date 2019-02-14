package coffeepartner.lancet.plugin;

import coffeepartner.capt.plugin.api.CaptInternal;
import coffeepartner.capt.plugin.api.Plugin;
import coffeepartner.capt.plugin.api.annotations.Def;
import coffeepartner.capt.plugin.api.process.AnnotationProcessor;
import coffeepartner.capt.plugin.api.transform.ClassTransformer;
import coffeepartner.lancet.plugin.extend.ScopeMatcher;
import coffeepartner.lancet.plugin.extend.impl.ChainedScopeMatcher;
import coffeepartner.lancet.plugin.extend.impl.DefaultScopeMatcher;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Def
public class LancetPlugin extends Plugin<CaptInternal> {

    private CaptInternal capt;
    private ScopeMatcher matcher;

    @Override
    public void onCreate(CaptInternal capt) throws IOException, InterruptedException {
        this.capt = capt;
        this.matcher = createMatcher();
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

    private ScopeMatcher createMatcher() {
        Map<String, Object> args = capt.getArgs().getMyArguments().arguments();
        return new ChainedScopeMatcher(Stream.concat(
                Stream.of(args.get("extraScopeMatchers"))
                        .filter(Objects::nonNull)
                        .flatMap(o -> Arrays.stream((String[]) o))
                        .map(s -> {
                            try {
                                return capt.captLoader().loadClass(s).asSubclass(ScopeMatcher.class).newInstance();
                            } catch (Exception e) {
                                throw new IllegalArgumentException("Create ScopeMatcher instance failed, class: " + s);
                            }
                        }),
                Stream.of(new DefaultScopeMatcher()) // use default at last
        ).collect(Collectors.toList()));
    }
}
