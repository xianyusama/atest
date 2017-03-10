package com.test;

import android.util.Log;

/**
 * Created by xiao on 2017/2/18.
 */

public class LogUtils {

    /**
     * 是否允许调试，默认为TRUE
     */
    private static boolean DEBUG = true;

    /**
     * @param DEBUG  是否允许调试，默认为TRUE(Setter)
     */
    public static void setDEBUG(boolean DEBUG) {
        LogUtils.DEBUG = DEBUG;
    }

    public static void v(String tag, String message) {
        if (DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (DEBUG) {
            Log.w(tag, message);

        }
    }

    public static void e(String tag, String message) {
        if (DEBUG) {
            Log.e(tag, message);
        }
    }

}
