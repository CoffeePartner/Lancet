package coffeepartner.lancet.rt;

/**
 * Created by gengwanpeng on 17/5/3.
 */
public final class Scope {

    public static final int SELF = 0x1;
    public static final int DIRECT = 0x2;
    public static final int LEAF = 0x4;
    public static final int ALL_CHILDREN = 0x8;
    public static final int ALL = ALL_CHILDREN | SELF;
}
