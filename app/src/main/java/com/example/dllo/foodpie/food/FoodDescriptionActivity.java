package com.example.dllo.foodpie.food;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseActivity;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.FoodDescriptionBean;
import com.example.dllo.foodpie.databean.FoodDescriptionPopBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/31.
 */
public class FoodDescriptionActivity extends BaseActivity implements View.OnTouchListener {

    private ListView lvFood;
    private FoodDescriptionLvAdapter adapter;
    private int id;
    private int group;
    private String url;
    //手指向右滑动时的最小速度
    private static final int XSPEED_MIN = 200;
    //手指向右滑动时的最小距离
    private static final int XDISTANCE_MIN = 250;
    //记录手指按下时的横坐标。
    private float xDown;
    //记录手指移动时的横坐标。
    private float xMove;
    //用于计算手指滑动的速度。
    private VelocityTracker mVelocityTracker;
    private LinearLayout ll;
    private TextView tvLine;

    private PopupWindow popUpWindow;
    private RecyclerView rvLine;

    @Override
    protected int getLayout() {
        return R.layout.activity_food_description;
    }

    @Override
    protected void initViews() {
        lvFood = bindView(R.id.lv_food_description);
        adapter = new FoodDescriptionLvAdapter(MyApp.getContext());
        ll = bindView(R.id.ll_food_description_back);
        tvLine = bindView(R.id.tv_food_description_line);

        lvFood.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FoodDescriptionActivity.this.onTouch(v, event);
                return false;
            }
        });
        //详情页面的pop点击事件
        tvLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popUpWindow == null || !popUpWindow.isShowing()){
                    showPopUpWindow();
                }
                //// TODO: 16/11/1 pop的显示和隐藏
            }
        });
    }

    private void showPopUpWindow() {
        popUpWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //2. 给pop添加一个view
        View view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.food_description_popwindow, null);
        rvLine = (RecyclerView) view.findViewById(R.id.rv_food_pop_line);


        popUpWindow.setContentView(view);
        Log.d("FoodDescriptionActivity", "tvLine:" + tvLine);

        popUpWindow.setOutsideTouchable(true);
        popUpWindow.setBackgroundDrawable(new BitmapDrawable());
//        popUpWindow = new PopupWindow(LayoutInflater.from(FoodDescriptionActivity.this).inflate(R.layout.food_description_popwindow, null), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        popUpWindow.showAsDropDown(btn, 100, 100);

        popUpWindow.showAsDropDown(tvLine, 0, 20);
        //pop页的网络请求
        initPopWeb();

    }

    @Override
    protected void initDate() {
        Intent intent = getIntent();
        id = intent.getIntExtra("Link", 0);
        group = intent.getIntExtra("Group", 0);
        Log.d("FoodDescriptionActivity", "group:" + group);
        if (group == 0){
            url = "http://food.boohee.com/fb/v1/foods?kind=group&value="+id + ""+"&order_by=1&page=1";
        }else if (group == 1){
            url = "http://food.boohee.com/fb/v1/foods?kind=brand&value="+id+""+"&order_by=1&page=1";
        }else {
            url = "http://food.boohee.com/fb/v1/foods?kind=restaurant&value="+id+""+"&order_by=1&page=1";
        }

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
        ll.setOnTouchListener(this);

    }

    private void initPopWeb() {
        String urlPop = "http://food.boohee.com/fb/v1/foods/sort_types";
        GsonRequest<FoodDescriptionPopBean> gsonRequest = new GsonRequest<FoodDescriptionPopBean>(FoodDescriptionPopBean.class, urlPop,
                        new Response.Listener<FoodDescriptionPopBean>() {

                            @Override
                            public void onResponse(FoodDescriptionPopBean response) {
                                //请求成功数据

                                FoodDescriptionPopRvAdapter adapterPop = new FoodDescriptionPopRvAdapter(MyApp.getContext());
                                adapterPop.setFoodDescriptionPopBean(response);
                                Log.d("FoodDescriptionActivity", "rvLine:" + rvLine);
                                rvLine.setAdapter(adapterPop);
                                GridLayoutManager manager = new GridLayoutManager(MyApp.getContext(), 3);
                                rvLine.setLayoutManager(manager);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        createVelocityTracker(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDown = event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                xMove = event.getRawX();
                int distanceX = (int) (xMove - xDown);
                int xSpeed = getScrollVelocity();
                if(distanceX > XDISTANCE_MIN && xSpeed > XSPEED_MIN) {
                    finish();
                    overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
                }
                break;
            case MotionEvent.ACTION_UP:
                recycleVelocityTracker();
                break;
            default:
                break;
        }
        return true;
    }

    private void createVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    private void recycleVelocityTracker() {
        mVelocityTracker.recycle();
        mVelocityTracker = null;
    }

    private int getScrollVelocity() {
        mVelocityTracker.computeCurrentVelocity(1000);
        int velocity = (int) mVelocityTracker.getXVelocity();
        return Math.abs(velocity);
    }

}
