package com.example.dllo.foodpie.food;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseActivity;
import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ParsedResultType;
import com.google.zxing.client.result.URIParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;

public class ScanActivity extends BaseActivity{
    private ScannerView sv;
    private Bundle bundle;
    public static final int RESULT_CODE = 1010; // 返回码
    public static final String SCAN_RESULT_KEY = "result";
    @Override
    protected int getLayout() {
        return R.layout.activity_scan;
    }

    @Override
    protected void initViews() {
        sv = (ScannerView) findViewById(R.id.sv);
    }

    @Override
    protected void initDate() {
        sv.setOnScannerCompletionListener(new OnScannerCompletionListener() {
            @Override
            public void OnScannerCompletion(Result result, ParsedResult parsedResult, Bitmap bitmap) {
                ParsedResultType type = parsedResult.getType();
                switch (type){
                    case URI:
                        URIParsedResult uriParsedResult = (URIParsedResult) parsedResult;
                        bundle.putString(SCAN_RESULT_KEY, uriParsedResult.getURI());
                        break;
                    case TEXT:
                        bundle.putString(SCAN_RESULT_KEY, result.getText());
                        break;
                    default:
                        bundle.putString(SCAN_RESULT_KEY,"其他的");
                }
                Intent intent = getIntent();
                intent.putExtra("bundle", bundle);
                setResult(RESULT_CODE, intent);
            }
        });
    }

    @Override
    protected void onResume() {
        sv.onResume();
        super.onResume();
    }
}