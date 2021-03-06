package stub.weaver.internal.parser.anno;

import org.objectweb.asm.tree.AnnotationNode;
import stub.weaver.internal.parser.AcceptableAnnoParser;
import stub.weaver.internal.parser.AnnotationMeta;

import java.util.Arrays;

/**
 * Created by gengwanpeng on 17/5/4.
 */
public class GatheredAcceptableAnnoParser implements AcceptableAnnoParser {

    public static GatheredAcceptableAnnoParser newInstance(AcceptableAnnoParser... acceptableAnnoParsers) {
        return new GatheredAcceptableAnnoParser(acceptableAnnoParsers);
    }

    private final AcceptableAnnoParser[] acceptableAnnoParsers;

    private GatheredAcceptableAnnoParser(AcceptableAnnoParser[] acceptableAnnoParsers) {
        this.acceptableAnnoParsers = acceptableAnnoParsers;
    }


    @Override
    public boolean accept(String desc) {
        return Arrays.stream(acceptableAnnoParsers).anyMatch(a -> a.accept(desc));
    }

    @Override
    public AnnotationMeta parseAnno(AnnotationNode annotationNode) {
        return Arrays.stream(acceptableAnnoParsers)
                .filter(a -> a.accept(annotationNode.desc))
                .findFirst().get().parseAnno(annotationNode);
    }
}
