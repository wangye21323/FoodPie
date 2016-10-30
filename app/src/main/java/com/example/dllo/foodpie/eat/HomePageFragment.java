package com.example.dllo.foodpie.eat;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.HomePageBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.SingleSimpleThreadPool;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * Created by dllo on 16/10/24.
 */
public class HomePageFragment extends BaseFragment {

    private PullLoadMoreRecyclerView rvHomePage;
    private HomePageRvAdapter adapter;
    private GridLayoutManager manager;
    int a = 1;
    private ImageButton btn_top;

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
        btn_top = bindView(R.id.btn_eat_homepage_top);
    }

    @Override
    protected void initData() {
        //将首页的网络请求放到单例线程池里边
        SingleSimpleThreadPool.getSimpleThreadPool().getPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
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
            }
        });

        SingleSimpleThreadPool.getSimpleThreadPool().getPoolExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //上拉下拉方法
                pullAndDownToFresh();
            }
        });
        btn_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvHomePage.scrollToTop();
                Toast.makeText(mContext, "回到顶部", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void pullAndDownToFresh() {
        rvHomePage.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                //下拉加载
                GsonRequest<HomePageBean> gsonRequest = new GsonRequest<HomePageBean>(HomePageBean.class, TheValues.EAT_HOMEPAGE,
                        new Response.Listener<HomePageBean>() {
                            @Override
                            public void onResponse(HomePageBean response) {
                                adapter.setArrayList(response);
                                //rvHomePage.setGridLayout(2);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                rvHomePage.setPullLoadMoreCompleted();

            }

            @Override
            public void onLoadMore() {
                //上拉刷新
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
                VolleySingleton.getInstance().addRequest(gsonRequest);
                a++;
                rvHomePage.setFooterViewText("");
                rvHomePage.setPullLoadMoreCompleted();
            }
        });
    }
}