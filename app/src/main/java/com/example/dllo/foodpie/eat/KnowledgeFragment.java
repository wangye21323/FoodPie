package com.example.dllo.foodpie.eat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.KnowledgeBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/24.
 */
public class KnowledgeFragment extends BaseFragment{

    private RecyclerView rvKnowledge;
    private KnowledgeRvAdapter adapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        rvKnowledge = bindView(R.id.rv_eat_knowledge);
        adapter = new KnowledgeRvAdapter(MyApp.getContext());
        rvKnowledge.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        GsonRequest<KnowledgeBean> gsonRequest = new GsonRequest<KnowledgeBean>(KnowledgeBean.class, TheValues.EAT_KOOWLEDGE,
                new Response.Listener<KnowledgeBean>() {

                    private LinearLayoutManager manager;

                    @Override
                    public void onResponse(KnowledgeBean response) {
                        adapter.setKnowledgeBean(response);
                        manager = new LinearLayoutManager(MyApp.getContext());
                        rvKnowledge.setLayoutManager(manager);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //第三步: 把请求放到请求队列里
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}