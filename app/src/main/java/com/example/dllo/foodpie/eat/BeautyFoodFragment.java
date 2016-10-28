package com.example.dllo.foodpie.eat;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.BeautyFoodBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/24.
 */
public class BeautyFoodFragment extends BaseFragment{

    private RecyclerView beautyFoodRv;
    private BeautyFoodRvAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_beautyfood;
    }

    @Override
    protected void initView() {
        beautyFoodRv = bindView(R.id.rv_eat_beautyfood);
        adapter = new BeautyFoodRvAdapter(MyApp.getContext());
        beautyFoodRv.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        GsonRequest<BeautyFoodBean> gsonRequest = new GsonRequest<BeautyFoodBean>(BeautyFoodBean.class, TheValues.EAT_BEAUTY,
                new Response.Listener<BeautyFoodBean>() {
                    @Override
                    public void onResponse(BeautyFoodBean response) {
                        adapter.setBeautyFoodBean(response);
                        LinearLayoutManager manager = new LinearLayoutManager(MyApp.getContext());
                        beautyFoodRv.setLayoutManager(manager);
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