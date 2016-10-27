package com.example.dllo.foodpie.eat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.databean.TestBean;

/**
 * Created by dllo on 16/10/24.
 */
public class TestFragment extends BaseFragment{

    private RecyclerView rvTest;
    private TestRvAdapter adapter;
    private LinearLayoutManager manager;

    @Override
    protected int getLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView() {
        rvTest = bindView(R.id.rv_eat_test);
        adapter = new TestRvAdapter(MyApp.getContext());
        rvTest.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        GsonRequest<TestBean> gsonRequest = new GsonRequest<TestBean>(TestBean.class, TheValues.EAT_TEST,
                new Response.Listener<TestBean>() {
                    @Override
                    public void onResponse(TestBean response) {
                        adapter.setTestBean(response);
                        manager = new LinearLayoutManager(MyApp.getContext());
                        rvTest.setLayoutManager(manager);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}