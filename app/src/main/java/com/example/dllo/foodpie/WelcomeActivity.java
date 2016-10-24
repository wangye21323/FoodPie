package com.example.dllo.foodpie;

import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.example.dllo.foodpie.base.BaseActivity;

public class WelcomeActivity extends BaseActivity{


    private ImageView imgWel;

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initViews() {
        imgWel = bindView(R.id.img_wel);
    }

    @Override
    protected void initDate() {
        //设置计时器,显示倒计时,完成倒计时跳转
        final CountDownTimer countDownTimer = new CountDownTimer(3000, 1000) {
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
