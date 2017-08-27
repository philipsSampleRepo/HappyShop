package com.luxola.sephora.happyshop.logger;

import android.util.Log;

/**
 * Created by Pradeep on 8/22/2017.
 */

public final class CommonLogger {
    public static CommonLogger log = null;
    public static boolean isDebug = true;

    public static void e(String tag, String error) {
        if (isDebug) {
            Log.e(tag, error);
        }
    }

    public static void d(String tag, String error) {
        if (isDebug) {
            Log.d(tag, error);
        }
    }

    public static void i(String tag, String error) {
        if (isDebug) {
            Log.i(tag, error);
        }
    }

    public static CommonLogger getInstance() {
        if (log == null) {
            log = new CommonLogger();
        }
        return log;
    }
}
