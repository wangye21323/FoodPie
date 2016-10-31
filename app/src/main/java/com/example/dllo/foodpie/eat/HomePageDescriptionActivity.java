package com.example.dllo.foodpie.eat;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseActivity;
import com.example.dllo.foodpie.databean.HomePageDescriptionBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.VolleySingleton;

import java.text.SimpleDateFormat;

public class HomePageDescriptionActivity extends BaseActivity {

    private ImageView imgDescription;
    private String text;
    private String Id;
    private TextView tvDescription;
    private ImageView imgDescriptionUser;
    private TextView tvDescriptionName;
    private TextView tvDescriptionData;
    private String urlImg;
    private String urlUser;
    private ImageButton iBtnBack;
    private String nowTimeFact;

    @Override
    protected int getLayout() {
        return R.layout.activity_home_page_description;
    }

    @Override
    protected void initViews() {
        imgDescription = bindView(R.id.img_eat_homepage_description);
        tvDescription = bindView(R.id.tv_eat_description);
        imgDescriptionUser = bindView(R.id.img_eat_homepage_description_user);
        tvDescriptionName = bindView(R.id.tv_eat_homepage_description_name);
        tvDescriptionData = bindView(R.id.tv_eat_homepage_description_data);
        iBtnBack = bindView(R.id.iBtn_Eat_back);
        initTime();//获取当前的时间
    }

    private void initTime() {
        //获取当前的时间
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd    hh:mm:ss");
        String NowTime = sDateFormat.format(new java.util.Date());
        String timeFact = (String) NowTime.subSequence(0, 10);
        nowTimeFact = timeFact.replace("-", "");
    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        Id = intent.getStringExtra("Web");
        text = intent.getStringExtra("Text");
        tvDescription.setText(text);

        String url = "http://food.boohee.com/fb/v1/food_cards/" + Id;
        GsonRequest<HomePageDescriptionBean> gsonRequest = new GsonRequest<HomePageDescriptionBean>(HomePageDescriptionBean.class, url,
                new Response.Listener<HomePageDescriptionBean>() {

                    @Override
                    public void onResponse(HomePageDescriptionBean response) {
                        urlUser = response.getUser_avatar();
                        urlImg = response.getImage_url();
                        tvDescriptionName.setText(response.getUser_name());
                        String time = response.getPost_date();
                        String timeFact = (String) time.subSequence(0, 10);
                        VolleySingleton.getInstance().getImage(urlUser, imgDescriptionUser);
                        VolleySingleton.getInstance().getImage(urlImg, imgDescription);

                        //当前时间和获取到的文章时间做差得出相差天数
                        int postData = (Integer.valueOf(nowTimeFact) - Integer.valueOf(timeFact.replace("-", "")));
                        String dataAgo = postData / 7 + "周前";
                        tvDescriptionData.setText(dataAgo);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);

        iBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}