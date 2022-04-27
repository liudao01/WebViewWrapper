package com.common.lib_model_web.fragment;

import android.os.Bundle;

import com.common.lib_model_web.R;
import com.common.lib_model_web.WebConstants;

/**
 * Created by xud on 2017/12/16.
 */

public class WebFragment extends BaseWebviewFragment {

    public static WebFragment newInstance(String keyUrl) {
        WebFragment fragment = new WebFragment();
        fragment.setArguments(getBundle(keyUrl));
        return fragment;
    }

    public static Bundle getBundle(String url) {
        Bundle bundle = new Bundle();
        bundle.putString(WebConstants.INTENT_TAG_URL, url);
        return bundle;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_webview;
    }

}
