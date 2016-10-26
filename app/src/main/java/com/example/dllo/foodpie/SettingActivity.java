package com.example.dllo.foodpie;

import android.view.View;
import android.widget.ImageView;

import com.example.dllo.foodpie.base.BaseActivity;

public class SettingActivity extends BaseActivity{

    private ImageView settingBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {
        settingBack = bindView(R.id.img_setting_img);
    }

    @Override
    protected void initDate() {
        settingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
