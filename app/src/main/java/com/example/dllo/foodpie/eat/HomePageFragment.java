package com.example.dllo.foodpie.eat;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.Web.GsonRequest;
import com.example.dllo.foodpie.Web.VolleySingleton;
import com.example.dllo.foodpie.base.BaseFragment;

/**
 * Created by dllo on 16/10/24.
 */
public class HomePageFragment extends BaseFragment{

    private RecyclerView rvHomePage;
    private String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";
    @Override
    protected int getLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        rvHomePage = bindView(R.id.rv_eat_homepage);
    }

    @Override
    protected void initData() {


        GsonRequest<HomePageBean> gsonRequest = new GsonRequest<HomePageBean>(HomePageBean.class, url,
                new Response.Listener<HomePageBean>() {
                    @Override
                    public void onResponse(HomePageBean response) {
                        //请求成功方法
                        HomePageRvAdapter adapter = new HomePageRvAdapter(getActivity());
                        adapter.setArrayList(response);
                        rvHomePage.setAdapter(adapter);

                        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
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