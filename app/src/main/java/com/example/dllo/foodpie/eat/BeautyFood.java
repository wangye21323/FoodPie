package com.example.dllo.foodpie.eat;

import android.support.v7.widget.RecyclerView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;

/**
 * Created by dllo on 16/10/24.
 */
public class BeautyFood extends BaseFragment{

    private RecyclerView rvBeautyFood;

    @Override
    protected int getLayout() {
        return R.layout.fragment_beautyfood;
    }

    @Override
    protected void initView() {
        rvBeautyFood = bindView(R.id.rv_eat_beautyfood);
    }

    @Override
    protected void initData() {

    }
}