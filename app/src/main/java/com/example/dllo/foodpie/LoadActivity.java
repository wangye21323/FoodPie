package com.example.dllo.foodpie;


import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dllo.foodpie.base.BaseActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoadActivity extends BaseActivity {


    private ImageView imgLogon;
    private EditText ediNum;
    private EditText ediPassword;
    private Button btnLoad;
    private TextView tv;

    @Override
    protected int getLayout() {
        return R.layout.activity_load;
    }

    @Override
    protected void initViews() {
        imgLogon = bindView(R.id.logon);
        ediNum = bindView(R.id.edt_load_num);
        ediPassword = bindView(R.id.edt_load_password);
        btnLoad = bindView(R.id.btn_load_load);
        tv = bindView(R.id.tv_load_time);
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

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 16/11/12 判断是是否是登录状态
                BmobUser bmobUser = BmobUser.getCurrentUser();
                if (bmobUser != null){
                    Toast.makeText(LoadActivity.this, "已经登录", Toast.LENGTH_SHORT).show();
                }else{
                    bmobUser = new BmobUser();
                    bmobUser.setUsername(ediNum.getText().toString());
                    bmobUser.setPassword(ediPassword.getText().toString());
                    bmobUser.login(new SaveListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if (e == null){
                                timeToJump();
                                Toast.makeText(LoadActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(LoadActivity.this, "登录失败, 请检查您的账号和密码是否输入错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public void timeToJump(){
        final CountDownTimer countDownTimer = new CountDownTimer(600, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tv.setText(((millisUntilFinished / 1000)) + "秒");
            }

            @Override
            public void onFinish() {
                finish();
            }

        };
        countDownTimer.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        BmobUser.logOut();
    }
}
