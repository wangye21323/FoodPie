package com.example.dllo.foodpie.my;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.SettingActivity;
import com.example.dllo.foodpie.base.BaseFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private ImageView imgSetting;

    @Override
    protected int getLayout() {
        return R.layout.fragment_my;
    }


    @Override
    protected void initView() {
        imgSetting = bindView(R.id.img_my_setting);
        imgSetting.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    //点击
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_my_setting:
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}