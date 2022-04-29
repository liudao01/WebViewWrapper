package com.madchan.webviewdemo;

import android.webkit.WebView;
import android.widget.Toast;

import com.common.lib_model_web.brige.CallBack;

import org.json.JSONObject;

import java.io.Serializable;

public class NativeMethods implements Serializable {

    public static void showToast(WebView view, JSONObject arg, CallBack callBack) {
        String message = arg.optString("msg");

        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

        if (callBack !=null) {
            try {
                JSONObject result = new JSONObject();
                result.put("msg", "NativeMethods js 调用 native 成功！");
                callBack.apply(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void showToast2(WebView view, JSONObject arg, CallBack callBack) {
        String message = arg.optString("msg");

        Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();

        if (callBack !=null) {
            try {
                JSONObject result = new JSONObject();
                result.put("msg", "NativeMethods js 调用 native 成功！");
                callBack.apply(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
