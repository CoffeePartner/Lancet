package coffeepartner.lancet.plugin.parser;

import coffeepartner.capt.plugin.api.OutputProvider;
import coffeepartner.capt.plugin.api.graph.ClassInfo;
import coffeepartner.lancet.plugin.ClassHandler;
import coffeepartner.lancet.plugin.bean.ClassBean;
import coffeepartner.lancet.plugin.util.Exclude;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okio.BufferedSource;
import org.objectweb.asm.tree.MethodNode;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class LancetParser {

    private static final String CACHE = "hook_cache.json";

    private final OutputProvider outputProvider;
    private final boolean incremental;
    private final Gson gson = new GsonBuilder()
            .addSerializationExclusionStrategy(new ExclusionStrategy() {
                @Override
                public boolean shouldSkipField(FieldAttributes f) {
                    return f.getAnnotation(Exclude.class) != null;
                }

                @Override
                public boolean shouldSkipClass(Class<?> clazz) {
                    return false;
                }
            })
            .create();
    private Map<String, ClassBean> preBean;
    private Map<>

    public LancetParser(OutputProvider outputProvider, boolean incremental) {
        this.outputProvider = outputProvider;
        this.incremental = incremental;
    }

    public void mayLoadPreCache() throws IOException {
        if (incremental) {
            BufferedSource bs = outputProvider.getProvider(OutputProvider.Type.CACHE).asSource(CACHE);
            preBean = gson.fromJson(new InputStreamReader(bs.inputStream()), new TypeToken<ConcurrentHashMap<String, ClassBean>>() {
            }.getType());
            bs.close();
        } else {
            preBean = new ConcurrentHashMap<>();
        }
    }

    Consumer<MethodNode> asClassParser(String className) {
        ClassBean classBean = preBean.remove(className);
        return m -> {

        };
    }

    public Set<String> collectExtras() {
        return preBean.values()
    }

    public ClassHandler.Factory asFactory() {
        return new ClassHandler.Factory() {
            @Nullable
            @Override
            public ClassHandler create(ClassInfo info, boolean required) {
                return null;
            }
        };
    }
}
