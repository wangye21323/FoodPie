package com.example.dllo.foodpie;

import android.content.Intent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dllo.foodpie.base.BaseActivity;

public class DescriptionActivity extends BaseActivity{

    private WebView webView;
    private String urlIntent;
    private ImageButton iBtnBack;
    private TextView tvDescription;
    private String text;

    @Override
    protected int getLayout() {
        return R.layout.activity_description;
    }

    @Override
    protected void initViews() {
        webView = bindView(R.id.wv_eat);
        iBtnBack = bindView(R.id.iBtn_Eat_back);
        tvDescription = bindView(R.id.tv_eat_description);
    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        urlIntent = intent.getStringExtra("Web");
        text = intent.getStringExtra("Text");
        tvDescription.setText(text);
        webView.loadUrl(urlIntent);
        //让网络图片适应手机屏幕
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置缓存
        webSettings.setAppCacheEnabled(true);
        // 设置缓存模式,一共有四种模式
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 支持缩放(适配到当前屏幕)
        webSettings.setSupportZoom(true);
        // 将图片调整到合适的大小
        webSettings.setUseWideViewPort(true);
        // 支持内容重新布局,一共有四种方式
        // 默认的是NARROW_COLUMNS
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        // 设置可以被显示的屏幕控制
        webSettings.setDisplayZoomControls(true);
//        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        iBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}