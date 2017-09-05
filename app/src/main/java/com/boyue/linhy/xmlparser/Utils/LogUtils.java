package com.boyue.linhy.xmlparser.Utils;

import android.util.Log;

/**
 * Created by linhy on 17-6-23.
 */

public class LogUtils {
    private static boolean canLog = false;

    public static void Log(String name, String value) {
        if (canLog) {
            Log.e(name, value);
        }
    }
}
