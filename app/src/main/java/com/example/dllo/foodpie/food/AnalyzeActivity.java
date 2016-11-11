package com.example.dllo.foodpie.food;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseActivity;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.EventAnalyzeBean;
import com.example.dllo.foodpie.databean.FoodAnalyzeBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.VolleySingleton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class AnalyzeActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgAddLeft;
    private ImageView imgAddRight;
    private String code;
    private String text;
    private TextView tvLeft;
    private TextView tvRight;
    private String name;
    private RecyclerView rv;
    private AnalyzeRvAdapter adapter;
    private ImageView imgDelLeft;
    private ImageView imgDelRight;
    private LinearLayout llBack;

    @Override
    protected int getLayout() {
        return R.layout.activity_analyze;
    }

    @Override
    protected void initViews() {
        imgAddLeft = bindView(R.id.img_food_analyze_add_left);
        imgAddRight = bindView(R.id.img_food_analyze_add_right);
        imgAddLeft.setOnClickListener(this);
        imgAddRight.setOnClickListener(this);

        tvLeft = bindView(R.id.tv_food_analyze_left);
        tvRight = bindView(R.id.tv_food_analyze_right);

        rv = bindView(R.id.rv_analyze);
        adapter = new AnalyzeRvAdapter();
        rv.setAdapter(adapter);

        imgDelLeft = bindView(R.id.img_food_analyze_add_left_del);
        imgDelRight = bindView(R.id.img_food_analyze_add_right_del);
        llBack = bindView(R.id.ll_food_analyze_title_back);

        imgDelLeft.setOnClickListener(this);
        imgDelRight.setOnClickListener(this);
        llBack.setOnClickListener(this);
    }

    @Override
    protected void initDate() {
        //注册eventBus
        EventBus.getDefault().register(this);
    }

    private void initAnalyzeWeb() {
        String imgUrl = "http://food.boohee.com/fb/v1/foods/" + code + "/brief?token=&user_key=&app_version=2.6";
        Log.d("AnalyzeActivity11", imgUrl);
        initAnalyzeData(imgUrl);

    }


    private void initAnalyzeData(String url) {
        GsonRequest<FoodAnalyzeBean> gsonRequest = new GsonRequest<FoodAnalyzeBean>(FoodAnalyzeBean.class, url,
                new Response.Listener<FoodAnalyzeBean>() {
                    @Override
                    public void onResponse(FoodAnalyzeBean response) {
                        if (text.equals("analyzeL")) {
                            tvLeft.setText(name);
                            //清空按键的显现
                            imgDelLeft.setVisibility(View.VISIBLE);
                            //设置img为不可点击状态
                            imgAddLeft.setEnabled(false);
                            //给adapter传值, 带上属性左右
                            adapter.setFoodAnalyzeBean("Left",response);
                            GridLayoutManager manager = new GridLayoutManager(MyApp.getContext(), 1);
                            rv.setLayoutManager(manager);
                            VolleySingleton.getInstance().getImage(response.getLarge_image_url(), imgAddLeft);
                        } else {
                            imgDelRight.setVisibility(View.VISIBLE);
                            tvRight.setText(name);
                            imgAddRight.setEnabled(false);
                            adapter.setFoodAnalyzeBean("Right",response);
                            GridLayoutManager manager = new GridLayoutManager(MyApp.getContext(), 1);
                            rv.setLayoutManager(manager);
                            VolleySingleton.getInstance().getImage(response.getLarge_image_url(), imgAddRight);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_food_analyze_add_left:
                Intent intentLeft = new Intent(this, SearchActivity.class);
                intentLeft.putExtra("analyze", "analyzeL");
                startActivity(intentLeft);
                break;
            case R.id.img_food_analyze_add_right:
                Intent intentRight = new Intent(this, SearchActivity.class);
                intentRight.putExtra("analyze", "analyzeR");
                startActivity(intentRight);
                break;
            case R.id.img_food_analyze_add_left_del:
                //清除按钮, 清除对应行数的内容
                adapter.clearLocation("Left");
                imgDelLeft.setVisibility(View.GONE);
                imgAddLeft.setImageResource(R.mipmap.add);
                //img点击可用
                imgAddLeft.setEnabled(true);
                tvLeft.setText("");
                break;
            case R.id.img_food_analyze_add_right_del:
                adapter.clearLocation("Right");
                imgDelRight.setVisibility(View.GONE);
                imgAddRight.setImageResource(R.mipmap.add);
                imgAddRight.setEnabled(true);
                tvRight.setText("");
                break;
            case R.id.ll_food_analyze_title_back:
                finish();
                break;
        }
    }

    //eventBus 接收第一个界面点击常用时带过来的值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventSetText(EventAnalyzeBean analyzeBean){
        text = analyzeBean.getText();
        code = analyzeBean.getCode();
        name = analyzeBean.getName();
        if (code != null) {
            initAnalyzeWeb();
        }
    }
    //解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}