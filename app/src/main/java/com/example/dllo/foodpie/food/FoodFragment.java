package com.example.dllo.foodpie.food;

import android.content.Intent;
import android.util.Log;
import android.widget.GridView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.FoodBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/21.
 */
public class FoodFragment extends BaseFragment implements OnClickFoodListener {

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


        adapterBrand = new FoodAdapter(MyApp.getContext());


        adapterChain = new FoodAdapter(MyApp.getContext());

    }

    @Override
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
    public void onClickFood(int link, int group) {
            Intent intent = new Intent(MyApp.getContext(), FoodDescriptionActivity.class);
            intent.putExtra("Link", link);
            intent.putExtra("Group", group);
        Log.d("FoodFragment", "group:" + group);
            startActivity(intent);
    }
}