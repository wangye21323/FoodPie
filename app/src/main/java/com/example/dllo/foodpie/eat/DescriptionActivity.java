package com.example.dllo.foodpie.eat;

import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
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