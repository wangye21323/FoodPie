package com.example.dllo.foodpie.collect;

import android.widget.ListView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;

/**
 * Created by dllo on 16/11/12.
 */
public class ArticleFragment extends BaseFragment{

    private ListView lv;

    @Override
    protected int getLayout() {
        return R.layout.fragment_artivle;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_collect_article);
    }

    @Override
    protected void initData() {

    }
}