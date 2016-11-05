package com.example.dllo.foodpie.food;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.FoodResultBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.VolleySingleton;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by dllo on 16/11/5.
 */
public class ResultFragment extends BaseFragment {

    private ListView lv;
    private String strUTF8;
    private ResultLvAdapter adapter;
    private String str1;
    private String a;
    private EditText edtTitle;

    @Override
    protected int getLayout() {
        return R.layout.fragment_result;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_food_search_result);
        adapter = new ResultLvAdapter(MyApp.getContext());
        edtTitle = bindView(R.id.edt_food_search);
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        String name = arguments.getString("name");

        if (name != null) {
            try {
                a = URLDecoder.decode(name, "UTF-8");
                Log.d("ResultFragment", a);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String url = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=&#x82F9;&#x679C";
        Log.d("ResultFragment1", url);

        GsonRequest<FoodResultBean> gsonRequest = new GsonRequest<FoodResultBean>(FoodResultBean.class, url,
                new Response.Listener<FoodResultBean>() {

                    @Override
                    public void onResponse(FoodResultBean response) {

                        adapter.setFoodResultBean(response);
                        Log.d("ResultFragment", "response:" + response);
                        Log.d("ResultFragment", "response.getItems().size():" + response.getItems().size());
                        lv.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
