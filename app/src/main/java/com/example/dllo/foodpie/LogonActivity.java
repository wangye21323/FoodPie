package com.example.dllo.foodpie;

import android.view.View;
import android.widget.Button;

import com.example.dllo.foodpie.base.BaseActivity;

public class LogonActivity extends BaseActivity{

    private Button btn;

    @Override
    protected int getLayout() {
        return R.layout.activity_logon;
    }

    @Override
    protected void initViews() {
        btn = bindView(R.id.btn_logon_logon);
    }

    @Override
    protected void initDate() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}