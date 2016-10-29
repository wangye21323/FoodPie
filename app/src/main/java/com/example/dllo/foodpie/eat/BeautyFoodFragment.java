package com.example.dllo.foodpie.eat;

import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.BeautyFoodBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * Created by dllo on 16/10/24.
 */
public class BeautyFoodFragment extends BaseFragment {

    private PullLoadMoreRecyclerView beautyFoodRv;
    private BeautyFoodRvAdapter adapter;
    int a = 0;

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
                        beautyFoodRv.setLinearLayout();
                        //LinearLayoutManager manager = new LinearLayoutManager(MyApp.getContext());
                        //beautyFoodRv.setLayoutManager(manager);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //第三步: 把请求放到请求队列里
        VolleySingleton.getInstance().addRequest(gsonRequest);

        beautyFoodRv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MyApp.getContext(), "下拉刷新", Toast.LENGTH_SHORT).show();
                GsonRequest<BeautyFoodBean> gsonRequest = new GsonRequest<BeautyFoodBean>(BeautyFoodBean.class, TheValues.EAT_BEAUTY,
                        new Response.Listener<BeautyFoodBean>() {
                            @Override
                            public void onResponse(BeautyFoodBean response) {
                                adapter.setBeautyFoodBean(response);
                                beautyFoodRv.setLinearLayout();
                                //LinearLayoutManager manager = new LinearLayoutManager(MyApp.getContext());
                                //beautyFoodRv.setLayoutManager(manager);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                //第三步: 把请求放到请求队列里
                VolleySingleton.getInstance().addRequest(gsonRequest);
                beautyFoodRv.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {

                Toast.makeText(MyApp.getContext(), "上拉加载", Toast.LENGTH_SHORT).show();
                String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=" + (a + 1) + "" + "&category=4&per=10";
                GsonRequest<BeautyFoodBean> gsonRequest = new GsonRequest<BeautyFoodBean>(BeautyFoodBean.class, url,
                        new Response.Listener<BeautyFoodBean>() {
                            @Override
                            public void onResponse(BeautyFoodBean response) {
                                adapter.setBeautyFoodBean(response);
                                beautyFoodRv.setLinearLayout();
                                //LinearLayoutManager manager = new LinearLayoutManager(MyApp.getContext());
                                //beautyFoodRv.setLayoutManager(manager);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                //第三步: 把请求放到请求队列里
                VolleySingleton.getInstance().addRequest(gsonRequest);
                a++;
                beautyFoodRv.setPullLoadMoreCompleted();
            }
        });

    }
}