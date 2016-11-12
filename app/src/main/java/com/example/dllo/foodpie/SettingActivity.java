package com.example.dllo.foodpie;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dllo.foodpie.base.BaseActivity;

import cn.bmob.v3.BmobUser;

public class SettingActivity extends BaseActivity {

    private ImageView settingBack;
    private Button btnLeave;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {
        settingBack = bindView(R.id.img_setting_img);
        btnLeave = bindView(R.id.btn_setting_leave);

        BmobUser bmobUser = BmobUser.getCurrentUser();
        if (bmobUser == null){
            btnLeave.setVisibility(View.GONE);
        }else {
            btnLeave.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void initDate() {
        settingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnLeave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();
                btnLeave.setVisibility(View.GONE);
                finish();
            }
        });
    }
}
