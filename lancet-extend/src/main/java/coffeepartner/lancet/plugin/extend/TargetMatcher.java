package coffeepartner.lancet.plugin.extend;

import java.util.Set;

public interface TargetMatcher {

    TargetMatcher setMethodName(String name);

    TargetMatcher intersectTargets(Set<String> targets);

    TargetMatcher setTargets(Set<String> targets);

    TargetMatcher setDesc();

    TargetMatcher unionAccess(int access);
}
