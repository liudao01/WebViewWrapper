package com.common.lib_model_web.brige;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.Toast;

import com.common.lib_model_web.uti.MainLooper;

/**
 * Created by liuml on 2022/4/27 15:11
 */
public class JavaScriptInterfaceApiBackup {

    private Context context;
    private WebView webView;

    public JavaScriptInterfaceApiBackup(Context context, WebView webView) {
        this.context = context;
        this.webView = webView;
    }


    @JavascriptInterface
    public void showChoosePicDialog() {
        MainLooper.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 这里可以写需要做的操作
                // ......
            }
        });
    }

    @JavascriptInterface
    public void jsCallAndroid() {
        Log.d("JavaScriptInterface", "jsCallAndroid: Js调用Android方法");
    }

    @JavascriptInterface
    public void jsCallAndroidArgs(String args) {
        Log.d("JavaScriptInterface", "jsCallAndroid: Js调用Android方法 args = " + args);
    }

    public void log(final String msg) {
        javaCallJS("log", msg);
           /* webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript: log(" + "'" + msg + "'" + ")");
                }
            });*/
    }
    public void javacalljs(final String msg) {
        javaCallJS("javacalljs", msg);
           /* webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadUrl("javascript: log(" + "'" + msg + "'" + ")");
                }
            });*/
    }


    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();

        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            log("show toast sucess");
        } else {
            log("show toast error");
        }
    }

    /**
     * js方法名 參數
     *
     * @param method
     * @param parameter
     */
    public void javaCallJS(String method, String parameter) {
        webView.loadUrl("javascript:" + method + "('" + parameter + "')");
    }


    public interface JavascriptCallback {
        public void onReceiveValue(String value);
    }

    public void executeJavascript(String script,
                                  final JavascriptCallback callback) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.evaluateJavascript(script, new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    if (callback != null) {
                        if (value != null && value.startsWith("\"")
                                && value.endsWith("\"")) {
//							value = value.substring(1, value.length() - 1)
//									.replaceAll("\\\\", "");
//                            value = StringEscapeUtils.unescapeJava(value.substring(1, value.length() - 1));
//                            LogUtil.d("value = " + value);
                        }
                        callback.onReceiveValue(value);
                    }
                }
            });
        } else {
            if (callback != null) {
//                myInterface.addCallback(++uniqueId + "", callback);
//                webView.loadUrl("javascript:window." + kInterface
//                        + ".onResultForScript(" + uniqueId + "," + script + ")");
            } else {
                webView.loadUrl("javascript:" + script);
            }
        }
    }
}