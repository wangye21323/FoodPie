package com.example.dllo.foodpie;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.foodpie.base.BaseActivity;

public class LoadActivity extends BaseActivity {


    private ImageView imgLogon;

    @Override
    protected int getLayout() {
        return R.layout.activity_load;
    }

    @Override
    protected void initViews() {
        imgLogon = bindView(R.id.logon);
    }

    @Override
    protected void initDate() {
        imgLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoadActivity.this, LogonActivity.class);
                startActivity(intent);
            }
        });
    }
}
