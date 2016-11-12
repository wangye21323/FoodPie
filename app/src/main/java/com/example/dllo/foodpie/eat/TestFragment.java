package com.example.dllo.foodpie.eat;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.TestBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * Created by dllo on 16/10/24.
 */
public class TestFragment extends BaseFragment implements OnClickItem {

    private PullLoadMoreRecyclerView rvTest;
    private TestRvAdapter adapter;
    private LinearLayoutManager manager;
    private int a = 1;
    private ImageButton btn_test;

    @Override
    protected int getLayout() {
        return R.layout.fragment_test;
    }

    @Override
    protected void initView() {
        rvTest = bindView(R.id.rv_eat_test);
        adapter = new TestRvAdapter(MyApp.getContext());
        rvTest.setAdapter(adapter);
        btn_test = bindView(R.id.btn_eat_test_top);

    }

    @Override
    protected void initData() {
        GsonRequest<TestBean> gsonRequest = new GsonRequest<TestBean>(TestBean.class, TheValues.EAT_TEST,
                new Response.Listener<TestBean>() {
                    @Override
                    public void onResponse(TestBean response) {
                        adapter.setTestBean(response);
                        manager = new LinearLayoutManager(MyApp.getContext());
                        rvTest.setLinearLayout();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);

        //上拉下拉方法
        pullAndDownToFresh();
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvTest.scrollToTop();
                Toast.makeText(mContext, "回到顶部", Toast.LENGTH_SHORT).show();
            }
        });
        //实现接口
        adapter.setOnClickItem(this);
    }

    private void pullAndDownToFresh() {
        rvTest.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                GsonRequest<TestBean> gsonRequest = new GsonRequest<TestBean>(TestBean.class, TheValues.EAT_TEST,
                        new Response.Listener<TestBean>() {
                            @Override
                            public void onResponse(TestBean response) {
                                adapter.setTestBean(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                rvTest.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                String url = TheValues.EAT_DOWN_BEFORE + (a + 1) + "" + TheValues.EAT_TEST_DOWN_AFTER;
                GsonRequest<TestBean> gsonRequest = new GsonRequest<TestBean>(TestBean.class, url,
                        new Response.Listener<TestBean>() {
                            @Override
                            public void onResponse(TestBean response) {
                                adapter.addBeanData(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                rvTest.setPullLoadMoreCompleted();
                rvTest.setFooterViewText("");
                a++;
            }
        });
    }

    @Override
    public void onClick(String link, String title) {
        Intent intent = new Intent(MyApp.getContext(), DescriptionActivity.class);
        intent.putExtra("Web", link);
        intent.putExtra("Text","咨询详情");
        intent.putExtra("Title", title);
        startActivity(intent);
    }

}