package net.nym.extendcomponent.common;

import net.nym.extendcomponent.BuildConfig;

/**
 * @author nym
 * @date 2014/9/29 0029.
 */
public class DebugConfig {
    public static final String TAG_BEDUG = "net.nym.extendcomponent";

    private static boolean IS_DEBUG ;

    static {
        /**
         * 默认
         *
         * */
            IS_DEBUG = BuildConfig.DEBUG;
    }

    private DebugConfig()
    {}

    public static void setDebug(boolean isDebug)
    {
        IS_DEBUG = isDebug;
    }

    public static boolean isDebug()
    {
        return IS_DEBUG;
    }
}
