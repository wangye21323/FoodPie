package com.example.dllo.foodpie.eat;

import android.support.v7.widget.GridLayoutManager;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.HomePageBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * Created by dllo on 16/10/24.
 */
public class HomePageFragment extends BaseFragment{

    private PullLoadMoreRecyclerView rvHomePage;
    private HomePageRvAdapter adapter;
    private GridLayoutManager manager;
    int a = 1;

    @Override
    protected int getLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initView() {
        rvHomePage = bindView(R.id.rv_eat_homepage);
        adapter = new HomePageRvAdapter(getActivity());
//        rvHomePage.setGridLayout(2);
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
                        rvHomePage.setGridLayout(2);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //第三步: 把请求放到请求队列里
        VolleySingleton.getInstance().addRequest(gsonRequest);

        pullAndDownToFresh();
    }

    private void pullAndDownToFresh() {
        rvHomePage.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(MyApp.getContext(), "下拉刷新", Toast.LENGTH_SHORT).show();
                GsonRequest<HomePageBean> gsonRequest = new GsonRequest<HomePageBean>(HomePageBean.class, TheValues.EAT_HOMEPAGE,
                        new Response.Listener<HomePageBean>() {
                            @Override
                            public void onResponse(HomePageBean response) {
                                adapter.setArrayList(response);
//                                rvHomePage.setGridLayout(2);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                //第三步: 把请求放到请求队列里
                VolleySingleton.getInstance().addRequest(gsonRequest);
                rvHomePage.setPullLoadMoreCompleted();

            }

            @Override
            public void onLoadMore() {

                Toast.makeText(MyApp.getContext(), "上拉加载", Toast.LENGTH_SHORT).show();


                String url = "http://food.boohee.com/fb/v1/feeds/category_feed?page=" + (a + 1) + "" + "&category=1&per=10";
                GsonRequest<HomePageBean> gsonRequest = new GsonRequest<HomePageBean>(HomePageBean.class, url,
                        new Response.Listener<HomePageBean>() {
                            @Override
                            public void onResponse(HomePageBean response) {
                                adapter.addBeanData(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                //第三步: 把请求放到请求队列里
                VolleySingleton.getInstance().addRequest(gsonRequest);
                a++;
                rvHomePage.setPullLoadMoreCompleted();
            }
        });
    }
}