package coffeepartner.lancet.plugin.parser;

import coffeepartner.lancet.plugin.bean.AnnotationBean;

public interface AnnotationMeta {

    AnnotationBean toBean();

    void visit(MetaCollector collector);

    interface Factory {
        AnnotationMeta fromBean(AnnotationBean bean);
    }
}
