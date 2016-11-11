package com.example.dllo.foodpie.my;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.dllo.foodpie.LoadActivity;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.SettingActivity;
import com.example.dllo.foodpie.base.BaseFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private ImageView imgSetting;
    private Button btnLoad;

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }


    @Override
    protected void initView() {
        imgSetting = bindView(R.id.img_my_setting);
        imgSetting.setOnClickListener(this);
        btnLoad = bindView(R.id.btn_my_to_load);
        btnLoad.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    //点击
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_my_setting:
                //点击跳转设置
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_my_to_load:
                //点击跳转登录界面
                Intent intent1 = new Intent(getContext(), LoadActivity.class);
                startActivity(intent1);
                break;
        }
    }
}