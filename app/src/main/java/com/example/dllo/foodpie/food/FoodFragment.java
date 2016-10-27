package com.example.dllo.foodpie.food;

import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.FoodBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.example.dllo.foodpie.base.BaseFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class FoodFragment extends BaseFragment{

    private GridView type;
    private GridView chain;
    private GridView brand;
    private FoodAdapter adapterType;
    private FoodAdapter adapterBrand;
    private FoodAdapter adapterChain;

    @Override
    protected int getLayout() {
        return R.layout.fragment_food;
    }

    @Override
    protected void initView() {
        type = bindView(R.id.gv_food_type);
        chain = bindView(R.id.gv_food_chain);
        brand = bindView(R.id.gv_food_brand);

        adapterType = new FoodAdapter(MyApp.getContext());
        type.setAdapter(adapterType);

        adapterBrand = new FoodAdapter(MyApp.getContext());
        brand.setAdapter(adapterBrand);

        adapterChain = new FoodAdapter(MyApp.getContext());
        chain.setAdapter(adapterChain);
    }

    @Override
    protected void initData() {

        GsonRequest<FoodBean> gsonRequest = new GsonRequest<FoodBean>(FoodBean.class, TheValues.FOOD_FOOD,
                new Response.Listener<FoodBean>() {

                    @Override
                    public void onResponse(FoodBean response) {

                        adapterType.setGroupBean(response.getGroup().get(0));

                        adapterBrand.setGroupBean(response.getGroup().get(1));

                        adapterChain.setGroupBean(response.getGroup().get(2));
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