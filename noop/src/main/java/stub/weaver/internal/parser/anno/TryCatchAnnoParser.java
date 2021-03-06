package stub.weaver.internal.parser.anno;

import org.objectweb.asm.tree.AnnotationNode;
import stub.weaver.internal.meta.HookInfoLocator;
import stub.weaver.internal.parser.AnnoParser;
import stub.weaver.internal.parser.AnnotationMeta;

/**
 * Created by gengwanpeng on 17/5/5.
 */
public class TryCatchAnnoParser implements AnnoParser {

    @Override
    public AnnotationMeta parseAnno(AnnotationNode annotationNode) {
        return new TryCatchAnnoMeta(annotationNode.desc);
    }

    public class TryCatchAnnoMeta extends AnnotationMeta {


        public TryCatchAnnoMeta(String desc) {
            super(desc);
        }

        @Override
        public void accept(HookInfoLocator locator) {
            locator.setTryCatch();
        }
    }
}
