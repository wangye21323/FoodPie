package com.example.dllo.foodpie.food;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseActivity;
import com.example.dllo.foodpie.databean.FoodAllBean;
import com.example.dllo.foodpie.databean.FoodAllDbBean;
import com.example.dllo.foodpie.dbtool.FoodAllDBTool;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.example.dllo.foodpie.weiget.CircleImageView;

import java.util.List;


public class FoodAllActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvTitle;
    private String code;
    private CircleImageView imgIcon;
    private TextView tvName;
    private TextView tvNum;
    private ImageView imgHealth;
    private TextView tvBody;
    private Button btnCompare;
    private TextView tvKa;
    private TextView tvJiao;
    private TextView tvUnit;
    private boolean isClick = false;
    private boolean isSelect = false;
    private ImageView imgBack;
    private ImageView imgHeart;
    private String iconUrl;
    private String name;
    private String num;

    @Override
    protected int getLayout() {
        return R.layout.activity_food_all;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        code = intent.getStringExtra("Code");//拼接网址
        Log.d("FoodAllActivity123", code);
        tvTitle = bindView(R.id.tv_food_collect_back_name);//标题的名字
        imgIcon = bindView(R.id.img_food_all_food);
        tvName = bindView(R.id.tv_food_all_name);
        tvNum = bindView(R.id.tv_food_all_num);
        imgHealth = bindView(R.id.img_food_all_img);
        tvBody = bindView(R.id.tv_food_all_text);
        btnCompare = bindView(R.id.btn_food_all_compare);
        imgBack = bindView(R.id.img_food_all_img1);
        imgBack.setOnClickListener(this);
        tvKa = bindView(R.id.tv_food_all_ka);
        tvKa.setOnClickListener(this);
        tvJiao = bindView(R.id.tv_food_all_jiao);
        tvJiao.setOnClickListener(this);
        tvUnit = bindView(R.id.tv_food_all_unit);

        imgHeart = bindView(R.id.img_food_all_heart);
        imgHeart.setOnClickListener(this);
        tvKa.setTextColor(Color.RED);
        tvJiao.setTextColor(Color.GRAY);
        tvUnit.setText("千卡");
    }

    @Override
    protected void initDate() {
        String url = TheValues.FOOD_ANALYZE_BEFORE + code;
        //进入三级界面的网络请求
        GsonRequest<FoodAllBean> gsonRequest = new GsonRequest<FoodAllBean>(FoodAllBean.class, url,
                new Response.Listener<FoodAllBean>() {
                    @Override
                    public void onResponse(FoodAllBean response) {
                        //请求成功数据
                        tvTitle.setText(response.getName());
                        iconUrl = response.getLarge_image_url();
                        VolleySingleton.getInstance().getImage(iconUrl, imgIcon);
                        name = response.getName();
                        tvName.setText(name);
                        num = response.getCalory();
                        tvNum.setText(num);
                        tvBody.setText(response.getAppraise());
                        switch (response.getHealth_light()) {
                            case 0:
                                imgHealth.setImageResource(R.mipmap.ic_recommend);
                                break;
                            case 1:
                                imgHealth.setImageResource(R.mipmap.ic_suit);
                                break;
                            case 2:
                                imgHealth.setImageResource(R.mipmap.ic_less);
                                break;
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);


        //点击对比
        btnCompare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodAllActivity.this, AnalyzeActivity.class);
                intent.putExtra("Code", code);
                startActivity(intent);
            }
        });

        //首次进入, 根据是否数据库有这个内容, 判断心是亮的还是暗的
        FoodAllDBTool.getInstance().queryByValuesFoodAllDbBean(FoodAllDbBean.class, "code", new String[]{code}, new FoodAllDBTool.OnQueryListener() {
            @Override
            public void onQuery(List<FoodAllDbBean> foodAllDbBean) {
                if (foodAllDbBean.size() > 0){
                    imgHeart.setImageResource(R.mipmap.ic_favorate_checked);
                    isSelect = !isSelect;
                }else {
                    imgHeart.setImageResource(R.mipmap.ic_favorate_unchecked);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_food_all_ka:
                if (isClick) {
                    tvKa.setTextColor(Color.RED);
                    tvJiao.setTextColor(Color.GRAY);
                    tvUnit.setText("千卡");
                    tvNum.setText(String.valueOf((int) (Integer.valueOf(tvNum.getText().toString()) * 0.2388)));
                    isClick = false;
                }
                break;
            case R.id.tv_food_all_jiao:
                if (!isClick) {
                    tvJiao.setTextColor(Color.RED);
                    tvKa.setTextColor(Color.GRAY);
                    tvNum.setText(String.valueOf((int) ((Integer.valueOf(tvNum.getText().toString()) + 1) / 0.2388)));
                    tvUnit.setText("千焦");
                    isClick = true;
                }
                break;
            case R.id.img_food_all_img1:
                finish();
                break;
            case R.id.img_food_all_heart:
                if (!isSelect) {
                    //当点击收藏的时候完成的操作
                    imgHeart.setImageResource(R.mipmap.ic_favorate_checked);
                    FoodAllDbBean bean = new FoodAllDbBean();
                    bean.setName(name);
                    bean.setLarge_image_url(iconUrl);
                    bean.setCalory(num);
                    bean.setCode(code);
                    //存入数据库
                    FoodAllDBTool.getInstance().insertFoodAllDbBean(bean);
                    isSelect = !isSelect;
                }else {
                    //把数据库的内容按照value值删除
                    FoodAllDBTool.getInstance().deleteValueBean(FoodAllDbBean.class,"name",new String[]{tvName.getText().toString()});
                    imgHeart.setImageResource(R.mipmap.ic_favorate_unchecked);
                    isSelect = !isSelect;
                }
                break;
        }
    }
}