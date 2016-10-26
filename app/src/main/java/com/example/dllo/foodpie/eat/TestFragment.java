package com.example.dllo.foodpie.eat;

import android.support.v7.widget.RecyclerView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;

/**
 * Created by dllo on 16/10/24.
 */
public class TestFragment extends BaseFragment{

    @Override
    protected int getLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView() {
        RecyclerView rvTest = bindView(R.id.rv_eat_test);
    }

    @Override
    protected void initData() {

    }
}