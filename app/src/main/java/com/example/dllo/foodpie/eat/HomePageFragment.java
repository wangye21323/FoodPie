package com.example.dllo.foodpie.eat;

import android.content.Intent;
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
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * Created by dllo on 16/10/24.
 */
public class HomePageFragment extends BaseFragment implements OnClickItem {

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
        rvHomePage.setAdapter(adapter);
        btn_top = bindView(R.id.btn_eat_homepage_top);
    }

    @Override
    protected void initData() {
        //首页信息的网络请求
        GsonRequest<HomePageBean> gsonRequest = new GsonRequest<HomePageBean>(HomePageBean.class, TheValues.EAT_HOMEPAGE,
                new Response.Listener<HomePageBean>() {
                    @Override
                    public void onResponse(HomePageBean response) {
                        adapter.setArrayList(response);
//                        manager = new GridLayoutManager(MyApp.getContext(), 2);
                        rvHomePage.setStaggeredGridLayout(2);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //第三步: 把请求放到请求队列里
        VolleySingleton.getInstance().addRequest(gsonRequest);

        //上拉下拉方法
        pullAndDownToFresh();

        btn_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvHomePage.scrollToTop();
                Toast.makeText(mContext, "回到顶部", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnClickItem(this);
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
                String url = TheValues.EAT_DOWN_BEFORE + (a + 1) + "" + TheValues.EAT_HOMEPAGE_DOWN_AFTER;
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

    @Override
    public void onClick(String link) {
        if (link.length() > 6){
            Intent intent = new Intent(MyApp.getContext(), DescriptionActivity.class);
            intent.putExtra("Web", link);
            intent.putExtra("Text","图片详情");
            startActivity(intent);
        }else{
            Intent intent = new Intent(MyApp.getContext(), HomePageDescriptionActivity.class);
            intent.putExtra("Web", link);
            intent.putExtra("Text","图片详情");
            startActivity(intent);
        }
    }



}