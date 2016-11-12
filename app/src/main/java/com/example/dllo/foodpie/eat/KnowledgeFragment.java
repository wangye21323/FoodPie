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
import com.example.dllo.foodpie.databean.KnowledgeBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

/**
 * Created by dllo on 16/10/24.
 */
public class KnowledgeFragment extends BaseFragment implements OnClickItem {

    private PullLoadMoreRecyclerView rvKnowledge;
    private KnowledgeRvAdapter adapter;
    private int a = 1;
    private ImageButton btn_knowledge;


    @Override
    protected int getLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView() {
        rvKnowledge = bindView(R.id.rv_eat_knowledge);
        adapter = new KnowledgeRvAdapter(MyApp.getContext());
        rvKnowledge.setAdapter(adapter);
        btn_knowledge = bindView(R.id.btn_eat_knowledge_top);

    }


    @Override
    protected void initData() {
        //网络请求
        GsonRequest<KnowledgeBean> gsonRequest = new GsonRequest<KnowledgeBean>(KnowledgeBean.class, TheValues.EAT_KNOWLEDGE,
                new Response.Listener<KnowledgeBean>() {

                    private LinearLayoutManager manager;

                    @Override
                    public void onResponse(KnowledgeBean response) {
                        adapter.setKnowledgeBean(response);
                        manager = new LinearLayoutManager(MyApp.getContext());
                        rvKnowledge.setLinearLayout();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);

        //上拉下拉方法
        pullAndDownToFresh();

        btn_knowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvKnowledge.scrollToTop();
                Toast.makeText(mContext, "回到顶部", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnClickItem(this);
    }

    private void pullAndDownToFresh() {
        rvKnowledge.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                GsonRequest<KnowledgeBean> gsonRequest = new GsonRequest<KnowledgeBean>(KnowledgeBean.class, TheValues.EAT_KNOWLEDGE,
                        new Response.Listener<KnowledgeBean>() {

                            private LinearLayoutManager manager;

                            @Override
                            public void onResponse(KnowledgeBean response) {
                                adapter.setKnowledgeBean(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                rvKnowledge.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                String url = TheValues.EAT_DOWN_BEFORE + (a + 1) + "" + TheValues.EAT_KNOWLEDGE_DOWN_AFTER;
                GsonRequest<KnowledgeBean> gsonRequest = new GsonRequest<KnowledgeBean>(KnowledgeBean.class, url,
                        new Response.Listener<KnowledgeBean>() {

                            @Override
                            public void onResponse(KnowledgeBean response) {
                                adapter.addBeanData(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                rvKnowledge.setPullLoadMoreCompleted();

                rvKnowledge.setFooterViewText("");
                a++;
            }
        });
    }

    @Override
    public void onClick(String link, String title) {
        Intent intent = new Intent(MyApp.getContext(), DescriptionActivity.class);
        intent.putExtra("Web", link);
        intent.putExtra("Text","咨询详情");
        startActivity(intent);
    }
}