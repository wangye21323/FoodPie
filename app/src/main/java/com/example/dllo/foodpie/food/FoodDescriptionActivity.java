package com.example.dllo.foodpie.food;

import android.content.Intent;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseActivity;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.FoodDescriptionBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/31.
 */
public class FoodDescriptionActivity extends BaseActivity{

    private ListView lvFood;
    private FoodDescriptionLvAdapter adapter;
    private String id;

    @Override
    protected int getLayout() {
        return R.layout.activity_food_description;
    }

    @Override
    protected void initViews() {
        lvFood = bindView(R.id.lv_food_description);
        adapter = new FoodDescriptionLvAdapter(MyApp.getContext());
    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        id = intent.getStringExtra("Food");
        String url = "http://food.boohee.com/fb/v1/foods?kind=group&value=1&order_by=1&page="+id;


        GsonRequest<FoodDescriptionBean> gsonRequest = new GsonRequest<FoodDescriptionBean>(FoodDescriptionBean.class, url,
                        new Response.Listener<FoodDescriptionBean>() {
                            @Override
                            public void onResponse(FoodDescriptionBean response) {
                                adapter.setFoodDescriptionBean(response);
                                lvFood.setAdapter(adapter);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
