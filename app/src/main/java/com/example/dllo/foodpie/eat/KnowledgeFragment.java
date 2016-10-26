package com.example.dllo.foodpie.eat;

import android.support.v7.widget.RecyclerView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;

/**
 * Created by dllo on 16/10/24.
 */
public class KnowledgeFragment extends BaseFragment{

    private RecyclerView rvKnowledge;

    @Override
    protected int getLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        rvKnowledge = bindView(R.id.rv_eat_knowledge);
    }

    @Override
    protected void initData() {

    }
}