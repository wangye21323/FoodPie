package com.example.dllo.foodpie;

import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.example.dllo.foodpie.base.BaseActivity;

import cn.bmob.v3.Bmob;

public class WelcomeActivity extends BaseActivity{


    private ImageView imgWel;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        imgWel = bindView(R.id.img_wel);

        //第一：默认初始化
        Bmob.initialize(this, "214cb2ec8524f909aca7cf0f220f4b2e");
    }

    @Override
    protected void initDate() {
        //设置计时器,显示倒计时,完成倒计时跳转
        final CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                imgWel.setImageResource(R.mipmap.welcome);
            }
            @Override
            public void onFinish() {

                    Intent intent = new Intent();
                    intent.setClass(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
            }

        };
        countDownTimer.start();

    }
}
