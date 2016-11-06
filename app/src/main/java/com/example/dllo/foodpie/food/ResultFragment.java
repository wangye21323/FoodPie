package com.example.dllo.foodpie.food;

import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private boolean flag = true;
    private CheckBox cb;

    @Override
    protected int getLayout() {
        return R.layout.fragment_result;
    }

    @Override
    protected void initView() {
        lv = bindView(R.id.lv_food_search_result);
        adapter = new ResultLvAdapter(MyApp.getContext());
        edtTitle = bindView(R.id.edt_food_search);
        cb = bindView(R.id.cb_search);
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        String name = arguments.getString("name");
        String title = arguments.getString("Title");

        if (name != null) {
            try {
                a = URLDecoder.decode(name, "UTF-8");
                Log.d("ResultFragment", a);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (title != null){
            try {
                a = URLDecoder.decode(title,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String url = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=" + a;
        initSearchListView(url);

        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    String url = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=" + a +"&health_light=1&token=qFz7beDQc8ZfXGtqihwG&user_key=ced05e81-44fd-4aae-b5a9-84e51d6ff136&app_version=2.6";
                    initSearchListView(url);
                }else{
                    String url = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=" + a +"&token=qFz7beDQc8ZfXGtqihwG&user_key=ced05e81-44fd-4aae-b5a9-84e51d6ff136&app_version=2.6";
                    initSearchListView(url);
                }
            }
        });

    }

    private void initSearchListView(String url){
//        //开启帧动画
//        if (!flag) {
//            animation.setVisibility(View.VISIBLE);
//        }
//        //获取到AnimationDrawable动画对象
//        animation.setImageResource(R.drawable.animation_food);
//        final AnimationDrawable anim = (AnimationDrawable) animation.getDrawable();
//        anim.start();

        GsonRequest<FoodResultBean> gsonRequest = new GsonRequest<FoodResultBean>(FoodResultBean.class, url,
                new Response.Listener<FoodResultBean>() {

                    @Override
                    public void onResponse(FoodResultBean response) {

                        adapter.setFoodResultBean(response);
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
