package com.example.dllo.foodpie.collect;

import android.widget.ListView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;

/**
 * Created by dllo on 16/11/12.
 */
public class FoodCollectFragment extends BaseFragment{

    private ListView lvCollect;

    @Override
    protected int getLayout() {
        return R.layout.fragment_food_collect;
    }

    @Override
    protected void initView() {
        lvCollect = bindView(R.id.lv_collect_food);
    }

    @Override
    protected void initData() {

    }
}