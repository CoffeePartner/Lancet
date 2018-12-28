package com.dieyidezui.lancet.plugin.resource;

import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.TransformInvocation;
import com.dieyidezui.lancet.plugin.api.OutputProvider;
import com.dieyidezui.lancet.plugin.api.Plugin;
import com.dieyidezui.lancet.plugin.cache.FileManager;
import com.dieyidezui.lancet.plugin.cache.OutputProviderFactory;
import com.dieyidezui.lancet.plugin.util.Constants;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.logging.Logger;
import org.gradle.api.logging.Logging;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.stream.Stream;

public class VariantResource implements Constants {

    private static final Logger LOGGER = Logging.getLogger(Loader.class);

    private final Loader loader = new Loader();
    private final String variant;
    private final FileManager files;
    private final OutputProviderFactory factory;
    private boolean incremental;

    public VariantResource(String variant, FileManager files, OutputProviderFactory factory) {
        this.variant = variant;
        this.files = files;
        this.factory = factory;
    }

    public String variant() {
        return variant;
    }

    public void init(TransformInvocation invocation, Configuration target) throws IOException {
        this.loader.initClassLoader(invocation, target);
        this.files.attachContext(incremental, invocation);
    }

    public void setIncremental(boolean incremental) {
        this.incremental = incremental;
    }

    public InputStream openStream(String className) throws IOException {
        URL is = loader.runtimeLoader.getResource(className + ".class");
        if (is == null) {
            throw new IOException("open class failed: " + className);
        }
        return is.openStream();
    }

    public Class<?> loadClass(String className) throws ClassNotFoundException {
        return loader.loadClass(className);
    }

    public URL loadPluginOnLancet(String pluginName) {
        return loader.loadPluginOnLancet(pluginName);
    }

    public boolean isIncremental() {
        return incremental;
    }

    public OutputProvider provider(String id) {
        return factory.newProvider(id);
    }

    public URLClassLoader loader() {
        return loader.runnerLoader;
    }

    static class Loader {

        private URLClassLoader runnerLoader;
        private URLClassLoader runtimeLoader;

        void initClassLoader(TransformInvocation invocation, Configuration target) {

            URL[] runnerUrls = target.resolve().stream()
                    .map(f -> {
                        try {
                            return f.toURI().toURL();
                        } catch (MalformedURLException e) {
                            throw new AssertionError(e);
                        }
                    }).toArray(URL[]::new);
            URLClassLoader lancetDependencies = URLClassLoader.newInstance(runnerUrls, Plugin.class.getClassLoader());

            URL[] runtimeUrls = invocation.getInputs().stream()
                    .flatMap(s -> Stream.concat(s.getDirectoryInputs().stream(), s.getJarInputs().stream()))
                    .map(QualifiedContent::getFile)
                    .map(f -> {
                        try {
                            return f.toURI().toURL();
                        } catch (MalformedURLException e) {
                            throw new AssertionError(e);
                        }
                    })
                    .toArray(URL[]::new);
            LOGGER.lifecycle("runner: {}", Arrays.toString(runnerUrls));
            LOGGER.lifecycle("runtime: {}", Arrays.toString(runtimeUrls));
            this.runnerLoader = URLClassLoader.newInstance(runtimeUrls, lancetDependencies);
            this.runtimeLoader = URLClassLoader.newInstance(runtimeUrls);
        }

        public Class<?> loadClass(String className) throws ClassNotFoundException {
            return runnerLoader.loadClass(className);
        }

        public URL loadPluginOnLancet(String pluginName) {
            // getParent() means apk resources are useless
            return runnerLoader.getParent().getResource(PLUGIN_PATH + pluginName + ".properties");
        }
    }

}
