package com.example.dllo.foodpie.eat;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
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
public class BeautyFoodFragment extends BaseFragment implements OnClickItem {

    private PullLoadMoreRecyclerView beautyFoodRv;
    private BeautyFoodRvAdapter adapter;
    private int a = 1;
    private ImageButton btn_beautyfood;

    @Override
    protected int getLayout() {
        return R.layout.fragment_beautyfood;
    }

    @Override
    protected void initView() {
        beautyFoodRv = bindView(R.id.rv_eat_beautyfood);
        adapter = new BeautyFoodRvAdapter(MyApp.getContext());
        beautyFoodRv.setAdapter(adapter);
        btn_beautyfood = bindView(R.id.btn_eat_beantyfood_top);
    }

    @Override
    protected void initData() {
        GsonRequest<BeautyFoodBean> gsonRequest = new GsonRequest<BeautyFoodBean>(BeautyFoodBean.class, TheValues.EAT_BEAUTY,
                new Response.Listener<BeautyFoodBean>() {
                    @Override
                    public void onResponse(BeautyFoodBean response) {
                        adapter.setBeautyFoodBean(response);
                        beautyFoodRv.setLinearLayout();
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

        btn_beautyfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                beautyFoodRv.scrollToTop();
                Toast.makeText(mContext, "回到顶部", Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnClickItem(this);
    }
    private void pullAndDownToFresh() {
        beautyFoodRv.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                GsonRequest<BeautyFoodBean> gsonRequest = new GsonRequest<BeautyFoodBean>(BeautyFoodBean.class, TheValues.EAT_BEAUTY,
                        new Response.Listener<BeautyFoodBean>() {
                            @Override
                            public void onResponse(BeautyFoodBean response) {
                                adapter.setBeautyFoodBean(response);
//                                beautyFoodRv.setLinearLayout();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                beautyFoodRv.setPullLoadMoreCompleted();
            }

            @Override
            public void onLoadMore() {
                String url = TheValues.EAT_DOWN_BEFORE + (a + 1) + "" + TheValues.EAT_BEAUTY_DOWN_AFTER;
                GsonRequest<BeautyFoodBean> gsonRequest = new GsonRequest<BeautyFoodBean>(BeautyFoodBean.class, url,
                        new Response.Listener<BeautyFoodBean>() {
                            @Override
                            public void onResponse(BeautyFoodBean response) {
                                adapter.addBeanData(response);
//                                beautyFoodRv.setLinearLayout();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                beautyFoodRv.setPullLoadMoreCompleted();
                beautyFoodRv.setFooterViewText("");
                a++;
            }
        });
    }

    @Override
    public void onClick(String link) {
        Intent intent = new Intent(MyApp.getContext(), DescriptionActivity.class);
        intent.putExtra("Web", link);
        intent.putExtra("Text","咨询详情");
        startActivity(intent);
    }

}