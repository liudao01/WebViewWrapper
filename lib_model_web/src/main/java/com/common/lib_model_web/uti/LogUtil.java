package com.common.lib_model_web.uti;


import android.util.Log;

import com.common.lib_model_web.BuildConfig;

/**
 * @author liuml.
 * @explain log工具类 tag值可以直接过滤为当前类
 * @time 2017/12/5 10:01
 */
public class LogUtil {
    public static boolean DEBUG = BuildConfig.DEBUG;

    /**
     * Don't let anyone instantiate this class.
     */
    private LogUtil() {
        throw new Error("Do not need instantiate!");
    }

    public static void d(String TAG, String msg) {
        if (DEBUG) {
            if (TAG == null || TAG.length() == 0
                    || msg == null || msg.length() == 0)
                return;

            int segmentSize = 3 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.d(TAG, msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.d(TAG, "-------------------" + logContent);
                }
                Log.d(TAG, "-------------------" + msg);// 打印剩余日志
            }
        }
    }


    public static void e(String tag, String msg) {
        if (DEBUG) {
            Log.e(tag, msg);
        }
    }


    public static void i(String tag, String msg) {
        if (DEBUG) {
            Log.i(tag, msg);
        }
    }


    public static void v(String tag, String msg) {
        if (DEBUG) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (DEBUG) {
            Log.v(tag, msg, tr);
        }
    }


    public static void w(String tag, String msg) {
        if (DEBUG) {
            Log.w(tag, msg);
        }
    }

    /**
     * Send a {@link Log#VERBOSE} log message.
     *
     * @param obj
     */
    public static void v(Object obj) {
        if (DEBUG) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.v(tag, msg);
        }
    }

    /**
     * @param obj
     */
    public static void d(Object obj) {
        if (DEBUG) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
//            Log.d(tag, msg);
            longlog(tag, msg);
        }

    }

    /**
     * Send an {@link Log#INFO} log message.
     *
     * @param obj
     */
    public static void i(Object obj) {
        if (DEBUG) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.i(tag, msg);
        }
    }
    public static void iRelease(Object obj) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.i(tag, msg);
    }

    /**
     * Send a {@link Log#WARN} log message.
     *
     * @param obj
     */
    public static void w(Object obj) {
        if (DEBUG) {
            String tag = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            Log.w(tag, msg);
        }
    }

    /**
     * Send an {@link Log#ERROR} log message.
     *
     * @param obj
     */
    public static void e(Object obj) {
//        if (DEBUG) {
//            String tag = getClassName();
//            String msg = obj != null ? obj.toString() : "obj == null";
//            Log.e(tag, msg);
//        }

        if (DEBUG) {
            String TAG = getClassName();
            String msg = obj != null ? obj.toString() : "obj == null";
            if (TAG == null || TAG.length() == 0
                    || msg == null || msg.length() == 0)
                return;

            int segmentSize = 3 * 1024;
            long length = msg.length();
            if (length <= segmentSize) {// 长度小于等于限制直接打印
                Log.e(TAG, msg);
            } else {
                while (msg.length() > segmentSize) {// 循环分段打印日志
                    String logContent = msg.substring(0, segmentSize);
                    msg = msg.replace(logContent, "");
                    Log.d(TAG, "-------------------" + logContent);
                }
                Log.e(TAG, "-------------------" + msg);// 打印剩余日志
            }
        }
    }

    private static String getClassName() {
        String result = "";
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        result = thisMethodStack.getClassName();
        return result;
    }

    public static void longlog(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        // 长度小于等于限制直接打印
        if (length <= segmentSize) {
            Log.d(tag, msg);
        } else {
            // 循环分段打印日志
            while (msg.length() > segmentSize) {
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.d(tag, logContent);
            }
            // 打印剩余日志
            Log.d(tag, msg);
        }
    }

}
