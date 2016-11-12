package com.example.dllo.foodpie;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dllo.foodpie.base.BaseActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LogonActivity extends BaseActivity {

    private Button btn;
    private EditText edtNum;
    private EditText edtPassward;
    private EditText edtPasswardAgain;

    @Override
    protected int getLayout() {
        return R.layout.activity_logon;
    }

    @Override
    protected void initViews() {
        btn = bindView(R.id.btn_logon_logon);
        edtNum = bindView(R.id.edt_num_logon);
        edtPassward = bindView(R.id.edt_password_logon);
        edtPasswardAgain = bindView(R.id.edt_password_logon_again);
    }

    @Override
    protected void initDate() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNum.getText().toString().equals("")|| edtPassward.getText().toString().equals("") || edtPasswardAgain.getText().toString().equals("")) {
                    Toast.makeText(LogonActivity.this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();

                } else if ((edtPassward != null && edtPasswardAgain != null) && !edtPassward.getText().toString().equals(edtPasswardAgain.getText().toString())) {
                    Toast.makeText(LogonActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                } else {
                    BmobUser bu = new BmobUser();
                    String num = edtNum.getText().toString();
                    bu.setUsername(num);
                    String password = edtPassward.getText().toString();
                    bu.setPassword(password);
                    //注意：不能用save方法进行注册
                    bu.signUp(new SaveListener<BmobUser>() {
                        @Override
                        public void done(BmobUser bmobUser, BmobException e) {
                            if (e == null) {
                                Toast.makeText(LogonActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                Toast.makeText(LogonActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }
}