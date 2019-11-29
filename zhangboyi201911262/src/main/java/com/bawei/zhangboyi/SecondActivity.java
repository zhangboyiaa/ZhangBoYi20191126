package com.bawei.zhangboyi;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bawei.zhangboyi.base.BaseActivity;

/**
 *移动
 * 1707A
 * 张博一
 * webview类
 **/
public class SecondActivity extends BaseActivity {


    private WebView webView;

    @Override
    protected void initData() {
        //接受传值
        final String key = getIntent().getStringExtra("key");
        webView.loadUrl(key);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(key);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.i("zby","开始");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.i("zby","结束");
            }
        });

        //hah
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.i("zby","进度为："+newProgress+"%");
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.i("zby","标题为："+title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                Log.i("zby","图片："+icon);
            }
        });
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.web_view);

        //辅助交互方法
        WebSettings settings = webView.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_second;
    }
}
