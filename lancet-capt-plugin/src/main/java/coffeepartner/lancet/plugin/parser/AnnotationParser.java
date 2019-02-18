package coffeepartner.lancet.plugin.parser;

import coffeepartner.lancet.plugin.bean.AnnotationBean;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnnotationParser implements AnnotationMeta.Factory {

    public static AnnotationMeta.Factory create() {
        return new AnnotationParser();
    }

    private final Map<String, TypedFactory> actualParsers;

    private AnnotationParser(TypedFactory... factories) {
        actualParsers = Stream.of(factories).collect(Collectors.toMap(TypedFactory::type, Function.identity()));
    }

    @Override
    public AnnotationMeta fromBean(AnnotationBean bean) {
        return ;
    }

    public interface TypedFactory extends AnnotationMeta.Factory {
        String type();
    }
}
