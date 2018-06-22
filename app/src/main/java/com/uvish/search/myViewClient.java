package com.uvish.search;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by uvish on 8/25/2017.
 */

class myViewClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        view.loadUrl(String.valueOf(request));
        return super.shouldOverrideUrlLoading(view, request);
    }
}
