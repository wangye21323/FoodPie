package com.example.dllo.foodpie.food;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.databean.FoodBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dllo on 16/10/21.
 */
public class FoodFragment extends BaseFragment implements OnClickFoodListener, View.OnClickListener {

    private GridView type;
    private GridView chain;
    private GridView brand;
    private FoodAdapter adapterType;
    private FoodAdapter adapterBrand;
    private FoodAdapter adapterChain;
    private RelativeLayout search;
    private LinearLayout llAnalyze;
    private String user;
    private LinearLayout llSweep;
    public static final int REQUEST_CODE = 1010;  // 请求码


    @Override
    protected int getLayout() {
        return R.layout.fragment_food;
    }

    @Override
    protected void initView() {
        type = bindView(R.id.gv_food_type);
        chain = bindView(R.id.gv_food_chain);
        brand = bindView(R.id.gv_food_brand);
        adapterType = new FoodAdapter(getActivity());
        adapterBrand = new FoodAdapter(getActivity());
        adapterChain = new FoodAdapter(getActivity());

        llSweep = bindView(R.id.ll_food_sweep);
        llSweep.setOnClickListener(this);

        search = bindView(R.id.rl_food_search);
        search.setOnClickListener(this);
        llAnalyze = bindView(R.id.ll_food_analyze);
        //食物百科搜索对比
        llAnalyze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索对比", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AnalyzeActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    //食物界面网络请求
    protected void initData() {
        GsonRequest<FoodBean> gsonRequest = new GsonRequest<FoodBean>(FoodBean.class, TheValues.FOOD_FOOD,
                new Response.Listener<FoodBean>() {

                    @Override
                    public void onResponse(FoodBean response) {

                        type.setAdapter(adapterType);
                        adapterType.setFoodBean(0, response);

                        brand.setAdapter(adapterBrand);
                        adapterBrand.setFoodBean(1, response);

                        adapterChain.setFoodBean(2, response);
                        chain.setAdapter(adapterChain);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //第三步: 把请求放到请求队列里
        VolleySingleton.getInstance().addRequest(gsonRequest);

        adapterType.setOnClickFoodListener(this);
        adapterBrand.setOnClickFoodListener(this);
        adapterChain.setOnClickFoodListener(this);


    }


    @Override
    public void onClickFood(int link, int group, String name, List categories) {
        Intent intent = new Intent(getActivity(), FoodDescriptionActivity.class);
        intent.putExtra("Link", link);
        intent.putExtra("Group", group);
        intent.putExtra("Name", name);
        intent.putExtra("categories", (Serializable) categories);

        Log.d("FoodFragment", "categories:" + categories);
        startActivity(intent);

    }

    @Override
    //食物百科界面的搜索按钮
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_food_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }

    }

}