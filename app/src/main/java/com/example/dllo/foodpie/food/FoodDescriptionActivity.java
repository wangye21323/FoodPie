package com.example.dllo.foodpie.food;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseActivity;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.FoodBean;
import com.example.dllo.foodpie.databean.FoodDescriptionBean;
import com.example.dllo.foodpie.databean.FoodDescriptionPopBean;
import com.example.dllo.foodpie.databean.FoodDescriptionRightPopBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/31.
 */
public class FoodDescriptionActivity extends BaseActivity implements View.OnTouchListener, OnClickPopRight, OnClickPopLeftListener {

    private PullToRefreshListView lvFood;
    private FoodDescriptionLvAdapter adapter;
    private int idBefore;
    private int group;
    private String url;
    private static final int XSPEED_MIN = 200;
    private static final int XDISTANCE_MIN = 50;
    private float xDown;
    private float xMove;
    private VelocityTracker mVelocityTracker;
    private LinearLayout ll;
    private TextView tvLine;
    private PopupWindow popUpWindow;
    private RecyclerView rvLine;
    private boolean isClick = false;
    private ImageView upOrDown;
    private int page = 1;
    private long dissmissTime = -1;
    private View test;
    private ImageView animation;
    private boolean flag = true;
    private LinearLayout llBack;
    private TextView tvName;
    private String name;
    private LinearLayout llAll;
    private TextView tvAll;
    private ImageView imgDown;
    private List<FoodBean.GroupBean.CategoriesBean.SubCategoriesBean> categories;
    private ListView lvRight;
    private PopupWindow popUpWindowRight;
    private ImageView imgPopRight;
    private PopRightLvAdapter adapterRight;
    private String urlRight;
    private FoodDescriptionPopRvAdapter adapterPop;
    private String urlLeft;
    private TextView tvPopName;
    private TextView tvDownOrUp;
    private ImageView imgDownOrUp;
    private LinearLayout llUpOrDown;

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
        upOrDown = bindView(R.id.food_description_up_down);
        animation = bindView(R.id.img_food_description_animation);
        test = bindView(R.id.test);
        llBack = bindView(R.id.ll_food_description_title_back);
        tvName = bindView(R.id.tv_food_description_back_name);
        llAll = bindView(R.id.ll_food_description_all);
        tvAll = bindView(R.id.tv_food_description_all);
        imgDown = bindView(R.id.img_food_description_down);
        imgPopRight = bindView(R.id.img_food_description_down);
        tvPopName = bindView(R.id.tv_food_description_line);

        tvDownOrUp = bindView(R.id.tv_food_description_down_up);
        imgDownOrUp = bindView(R.id.img_food_description_down_up);
        llUpOrDown = bindView(R.id.ll_food_description_down_up);

        adapterPop = new FoodDescriptionPopRvAdapter(this);

        adapterRight = new PopRightLvAdapter(this);




        //listview的左侧占位
        test.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                FoodDescriptionActivity.this.onTouch(v, event);
                return true;
            }
        });
        //左侧popUpWindow的初始化
        initPopupLeft();
        //右侧popUpWindow的初始化
        initPopupRight();
        //详情页面的pop点击事件
        tvLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!popUpWindow.isShowing()) {
                    upOrDown.setImageResource(R.mipmap.ic_unit_arrow_down);
                }
                long currentTime = System.currentTimeMillis();
                if (currentTime - dissmissTime < 300) {
                    return;
                }
                if (!popUpWindow.isShowing()) {
                    showPopUpWindow();
                    upOrDown.setImageResource(R.mipmap.ic_unit_arrow_up);
                } else {
                    popUpWindow.dismiss();
                }
            }
        });

        //返回按钮
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //详情界面的上拉加载
        lvFood.setMode(PullToRefreshBase.Mode.PULL_FROM_END);
        lvFood.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                //下拉方法, 这个里边用不到
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

                //开启帧动画
                if (!flag) {
                    animation.setVisibility(View.VISIBLE);
                }
                //获取到AnimationDrawable动画对象
                animation.setImageResource(R.drawable.animation_food);
                final AnimationDrawable anim = (AnimationDrawable) animation.getDrawable();
                anim.start();

                page++;
                if (group == 0) {
                    url = TheValues.FOOD_DESCRIPTION_DOWN_GROUP_BEFORE + idBefore + TheValues.FOOD_DESCRIPTION_DOWN_AFTER + page + "";
                } else if (group == 1) {
                    url = TheValues.FOOD_DESCRIPTION_DOWN_BRAND_BEFORE + idBefore + TheValues.FOOD_DESCRIPTION_DOWN_AFTER + page + "";
                } else {
                    url = TheValues.FOOD_DESCRIPTION_DOWN_RESTAURANT_BEFORE + idBefore + TheValues.FOOD_DESCRIPTION_DOWN_AFTER + page + "";
                }

                GsonRequest<FoodDescriptionBean> gsonRequest = new GsonRequest<FoodDescriptionBean>(FoodDescriptionBean.class, url,
                        new Response.Listener<FoodDescriptionBean>() {
                            @Override
                            public void onResponse(FoodDescriptionBean response) {
                                adapter.addBeanData(response);
                                animation.setVisibility(View.INVISIBLE);
                                flag = false;
                                anim.stop();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                VolleySingleton.getInstance().addRequest(gsonRequest);
                lvFood.onRefreshComplete();
            }
        });
        //由高到低排序和由低到高排序
        llUpOrDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (urlLeft != null){
                    if (!isClick){
                        tvDownOrUp.setText("由低到高");
                        imgDownOrUp.setImageResource(R.mipmap.ic_arrow_up_selected);
                        isClick = !isClick;
                        String urlToUp = urlLeft+"&order_asc=1";

                        initGsonRequest(urlToUp);
                    }else{
                        tvDownOrUp.setText("由高到低");
                        imgDownOrUp.setImageResource(R.mipmap.ic_arrow_down_selected);
                        isClick = !isClick;
                        String urlToDown = urlLeft+"&order_asc=0";

                        initGsonRequest(urlToDown);
                    }
                }
            }
        });
    }

    private void initPopupRight() {
        popUpWindowRight = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //2. 给pop添加一个view
        View view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.food_description_popwindow_right, null);
        lvRight = (ListView) view.findViewById(R.id.lv_food_description_right);
        popUpWindowRight.setContentView(view);

        popUpWindowRight.setOutsideTouchable(true);
        popUpWindowRight.setBackgroundDrawable(new BitmapDrawable());
    }

    public void initPopupLeft() {
        popUpWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //2. 给pop添加一个view
        View view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.food_description_popwindow, null);
        rvLine = (RecyclerView) view.findViewById(R.id.rv_food_pop_line);
        popUpWindow.setContentView(view);
        popUpWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dissmissTime = System.currentTimeMillis();
            }
        });
        popUpWindow.setOutsideTouchable(true);
        popUpWindow.setBackgroundDrawable(new BitmapDrawable());
        //pop页的网络请求
        initPopWeb();
    }

    private void showPopUpWindow() {
        popUpWindow.showAsDropDown(tvLine, 0, 20);
    }

    private void showRightPopUpWindow() {
        popUpWindowRight.showAsDropDown(imgPopRight, 10, -40);
    }


    @Override
    protected void initDate() {
        Intent intent = getIntent();
        idBefore = intent.getIntExtra("Link", 0);
        group = intent.getIntExtra("Group", 0);
        name = intent.getStringExtra("Name");
        tvName.setText(name);
        categories = (List<FoodBean.GroupBean.CategoriesBean.SubCategoriesBean>) this.getIntent().getSerializableExtra("categories");


        if (group == 0) {
            tvAll.setText("全部");
            imgDown.setImageResource(R.mipmap.ic_food_arrow_ordering);
            url = TheValues.FOOD_DESCRIPTION_DOWN_GROUP_BEFORE + idBefore + TheValues.FOOD_DESCRIPTION_DOWN_AFTER;
        } else if (group == 1) {

            url = TheValues.FOOD_DESCRIPTION_DOWN_BRAND_BEFORE + idBefore + TheValues.FOOD_DESCRIPTION_DOWN_AFTER;
        } else {
            url = TheValues.FOOD_DESCRIPTION_DOWN_RESTAURANT_BEFORE + idBefore + TheValues.FOOD_DESCRIPTION_DOWN_AFTER;
        }


        //首次进入页面的时候进行的网络请求
        initGsonRequest(url);
        ll.setOnTouchListener(this);

        //右上角的点击全部按钮, 点击跳出pop
        llAll.setOnClickListener(new View.OnClickListener() {

            private FoodDescriptionRightPopBean bean;
            private FoodDescriptionRightPopBean bean1;

            @Override
            public void onClick(View v) {
                if (!popUpWindowRight.isShowing()) {
                    showRightPopUpWindow();
                }
                ArrayList<FoodDescriptionRightPopBean> arrayList = new ArrayList<FoodDescriptionRightPopBean>();

                bean1 = new FoodDescriptionRightPopBean(0, "全部");
                arrayList.add(bean1);
                for (int i = 0; i < categories.size(); i++) {
                    bean = new FoodDescriptionRightPopBean(categories.get(i).getId(), categories.get(i).getName());

                    arrayList.add(bean);
                }

                adapterRight.setFoodDescriptionRightPopBean(arrayList);
                lvRight.setAdapter(adapterRight);


            }
        });
        adapterRight.setOnClickPopRight(this);


        adapterPop.setOnClickPopLeftListener(this);
    }

    //popWindow页面的网络请求
    private void initPopWeb() {
        GsonRequest<FoodDescriptionPopBean> gsonRequest = new GsonRequest<FoodDescriptionPopBean>(FoodDescriptionPopBean.class, TheValues.FOOD_DESCRIPTION_LINE,
                new Response.Listener<FoodDescriptionPopBean>() {

                    @Override
                    public void onResponse(FoodDescriptionPopBean response) {
                        //请求成功数据

                        adapterPop.setFoodDescriptionPopBean(response);
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
    //滑动退出
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
                if (distanceX > XDISTANCE_MIN && xSpeed > XSPEED_MIN) {
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


    //详情页右侧的pop内容的点击事件
    @Override
    public void onClickRight(int id, String name) {
        popUpWindowRight.dismiss();
        tvAll.setText(name);
        //开启帧动画
        if (!flag) {
            animation.setVisibility(View.VISIBLE);
        }
        //获取到AnimationDrawable动画对象
        animation.setImageResource(R.drawable.animation_food);
        final AnimationDrawable anim = (AnimationDrawable) animation.getDrawable();
        anim.start();

        Log.d("FoodDescriptionActivity", "id:" + id);
        Log.d("FoodDescriptionActivity", "idBefore:" + idBefore);
        Log.d("FoodDescriptionActivity", "group:" + group);

        if (id == 0){
            Log.d("FoodDescriptionActivity", "点击了全部");
            if (group == 0) {
                urlRight = TheValues.FOOD_DESCRIPTION_DOWN_GROUP_BEFORE + idBefore + "" + TheValues.FOOD_DESCRIPTION_DOWN_AFTER;
            } else if (group == 1) {
                urlRight = TheValues.FOOD_DESCRIPTION_DOWN_BRAND_BEFORE + idBefore + "" + TheValues.FOOD_DESCRIPTION_DOWN_AFTER;
            } else {
                urlRight = TheValues.FOOD_DESCRIPTION_DOWN_RESTAURANT_BEFORE + idBefore + "" + TheValues.FOOD_DESCRIPTION_DOWN_AFTER;
            }
        }else{
                urlRight = "http://food.boohee.com/fb/v1/foods?kind=group&value="+idBefore + ""+"&sub_value=" + id + "" + "&order_by=1&page=1&order_asc=0";
        }

        initGsonRequest(urlRight);
    }

    //左侧pop的内容点击事件
    @Override
    public void onClickPopLeft(String index, String name) {
        tvPopName.setText(name);
        tvDownOrUp.setText("由高到低");
        imgDownOrUp.setImageResource(R.mipmap.ic_arrow_down_selected);
        Log.d("FoodDescriptionActivity", "group:" + group);
        Log.d("FoodDescriptionActivity", index);
        Log.d("FoodDescriptionActivity", "idBefore:" + idBefore);
        if (group == 0){
            urlLeft = "http://food.boohee.com/fb/v1/foods?kind=group&value="+idBefore+"&order_by="+ index +"&page=1";
        }else if (group == 1){
            urlLeft = "http://food.boohee.com/fb/v1/foods?kind=brand&value="+idBefore+"&order_by="+ index +"&page=1";
        }else{
            urlLeft = "http://food.boohee.com/fb/v1/foods?kind=restaurant&value="+idBefore+"&order_by="+ index +"&page=1";
        }
        Log.d("FoodDescriptionActivity", urlLeft);

        //网络请求
        initGsonRequest(urlLeft);

        popUpWindow.dismiss();


    }

    //网络请求的方法, 传递参数即可
    private void initGsonRequest(String url){
        //开启帧动画
        if (!flag) {
            animation.setVisibility(View.VISIBLE);
        }
        //获取到AnimationDrawable动画对象
        animation.setImageResource(R.drawable.animation_food);
        final AnimationDrawable anim = (AnimationDrawable) animation.getDrawable();
        anim.start();

        GsonRequest<FoodDescriptionBean> gsonRequest = new GsonRequest<FoodDescriptionBean>(FoodDescriptionBean.class, url,
                new Response.Listener<FoodDescriptionBean>() {
                    @Override
                    public void onResponse(FoodDescriptionBean response) {
                        adapter.setFoodDescriptionBean(response);
                        lvFood.setAdapter(adapter);
                        animation.setVisibility(View.INVISIBLE);
                        flag = false;
                        anim.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}
