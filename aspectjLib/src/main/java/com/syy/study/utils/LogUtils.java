package com.syy.study.utils;

import android.util.Log;

import com.syy.study.BuildConfig;

public class LogUtils {
    private static boolean isDebug = BuildConfig.DEBUG;
    private LogUtils() {}

    public static void log(String tag, String message) {
        if (isDebug) {
            Log.d(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (isDebug) {
            Log.e(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (isDebug) {
            Log.i(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (isDebug) {
            Log.d(tag, message);
        }
    }
}
