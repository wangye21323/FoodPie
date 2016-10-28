package com.example.dllo.foodpie.eat;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.databean.HomePageBean;

/**
 * Created by dllo on 16/10/24.
 */
public class HomePageFragment extends BaseFragment{

    private RecyclerView rvHomePage;
    private HomePageRvAdapter adapter;
    private GridLayoutManager manager;

    @Override
    protected int getLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        rvHomePage = bindView(R.id.rv_eat_homepage);
        adapter = new HomePageRvAdapter(getActivity());
        rvHomePage.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        GsonRequest<HomePageBean> gsonRequest = new GsonRequest<HomePageBean>(HomePageBean.class, TheValues.EAT_HOMEPAGE,
                new Response.Listener<HomePageBean>() {
                    @Override
                    public void onResponse(HomePageBean response) {
                        adapter.setArrayList(response);
                        manager = new GridLayoutManager(MyApp.getContext(), 2);
                        rvHomePage.setLayoutManager(manager);
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