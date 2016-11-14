package com.example.dllo.foodpie.my;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.foodpie.LoadActivity;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.SettingActivity;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.collect.CollectActivity;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/10/21.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {

    private ImageView imgSetting;
    private Button btnLoad;
    private ImageView imgIcon;
    private TextView tvUserName;
    private String user;
    private Button btnChange;
    private RelativeLayout rvCollect;
    private BmobUser bmobUser;

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
        imgIcon = bindView(R.id.img_my_person_icon);
        tvUserName = bindView(R.id.tv_my_user_name);
        btnChange = bindView(R.id.btn_my_change);
        rvCollect = bindView(R.id.ll_my_collect);
        rvCollect.setOnClickListener(this);
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
            case R.id.ll_my_collect:
                if (bmobUser != null){
                    Intent intent2 = new Intent(getActivity(), CollectActivity.class);
                    startActivity(intent2);
                }else {
                    Intent intent2 = new Intent(getContext(), LoadActivity.class);
                    startActivity(intent2);
                }
                break;

        }
    }
    @Override
    public void onResume() {
        super.onResume();
        //判断当前是不是登录状态
        bmobUser = BmobUser.getCurrentUser();
        //当前状态是登录状态下
        if (bmobUser != null){
            tvUserName.setVisibility(View.VISIBLE);
            tvUserName.setText(bmobUser.getUsername());
            btnLoad.setVisibility(View.GONE);
            btnChange.setVisibility(View.VISIBLE);
            imgIcon.setImageResource(R.mipmap.liushishi);
        }else {
            tvUserName.setVisibility(View.INVISIBLE);
            btnChange.setVisibility(View.INVISIBLE);
            btnLoad.setVisibility(View.VISIBLE);
            imgIcon.setImageResource(R.mipmap.ic_analyse_default);
        }
    }



}