package com.example.dllo.foodpie.food;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.FoodResultBean;
import com.example.dllo.foodpie.databean.FoodSearchPopBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by dllo on 16/11/5.
 */
public class ResultFragment extends BaseFragment implements OnClickPopLeftListener {

    private PullToRefreshListView lv;
    private String strUTF8;
    private ResultLvAdapter adapter;
    private String str1;
    private String UTF;
    private EditText edtTitle;
    private boolean flag = true;
    private boolean isClick = false;
    private CheckBox cb;
    private LinearLayout ll;
    private PopupWindow popUpWindow;
    private RecyclerView rvLine;
    private FoodSearchPopRvAdapter adapterPop;
    private long dissmissTime = -1;
    private ImageView upOrDown;
    private String nameStr;
    private String url;
    private ImageView animation;
    private int page = 1;
    private LinearLayout llUpDown;
    private TextView tvUpDown;
    private ImageView imgUpDown;
    private LinearLayout llCheckBox;
    private String urlToUp;
    private String urlToDown;
    private TextView tvPop;


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
        ll = bindView(R.id.ll_food_search_line);
        upOrDown = bindView(R.id.food_search_up_down);
        animation = bindView(R.id.img_food_search_animation);
        llUpDown = bindView(R.id.ll_food_search_down_up);
        llCheckBox = bindView(R.id.ll_food_search_down_up_check);

        tvUpDown = bindView(R.id.tv_food_search_up_down);
        imgUpDown = bindView(R.id.img_food_search_up_down);

        tvPop = bindView(R.id.tv_food_search_line_pop);

        //左侧popUpWindow的初始化
        initPopupLeft();

        //第二个界面的营养素排序的点击事件
        ll.setOnClickListener(new View.OnClickListener() {
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

        adapterPop = new FoodSearchPopRvAdapter(MyApp.getContext());
    }


    private void initPopupLeft() {//初始化pop
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
        //当点击其他地方时, pop消失
        popUpWindow.setOutsideTouchable(true);
        popUpWindow.setBackgroundDrawable(new BitmapDrawable());
        //pop页的网络请求
        initPopWeb();
    }

    //pop界面的网络请求
    private void initPopWeb() {
        GsonRequest<FoodSearchPopBean> gsonRequest = new GsonRequest<FoodSearchPopBean>(FoodSearchPopBean.class, TheValues.FOOD_DESCRIPTION_LINE,
                new Response.Listener<FoodSearchPopBean>() {

                    @Override
                    public void onResponse(FoodSearchPopBean response) {
                        //自定义添加一个常见选项
                        FoodSearchPopBean.TypesBean typesBean = new FoodSearchPopBean.TypesBean();
                        typesBean.setName("常见");
                        typesBean.setIndex("1");
                        typesBean.setCode("common");
                        response.getTypes().add(0, typesBean);
                        //请求成功数据
                        adapterPop.setFoodSearchPopBean(response);
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

    private void showPopUpWindow() {
        //让pop显示在控件的相对位置
        popUpWindow.showAsDropDown(ll, 0, 20);
    }

    @Override
    protected void initData() {
        //接收来自上一个界面的传值
        Bundle arguments = getArguments();
        String name = arguments.getString("name");
        String title = arguments.getString("Title");

        //传递过来的汉字转换成为
        if (name != null) {
            try {
                UTF = URLEncoder.encode(name, "utf-8");
                Log.d("ResultFragment", UTF);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else if (title != null) {
            try {
                UTF = URLEncoder.encode(title, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        //点击搜索时的网络请求, 首次进入第二个界面的显示
        String url = TheValues.FOOD_SEARCH_SECOND_LV_BEFORE + UTF;
        initSearchListView(url);
        //推荐食物部分的点击事件, 切换checkbox
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    String url = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=" + UTF + "&health_light=1";
                    String url = TheValues.FOOD_SEARCH_SECOND_CHECK_BEFORE + UTF + "&health_light=1";
                    initSearchListView(url);
                } else {
//                    String url = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=" + UTF;
                    String url = TheValues.FOOD_SEARCH_SECOND_CHECK_BEFORE + UTF;
                    initSearchListView(url);
                }
            }
        });

        //pop界面的点击事件
        adapterPop.setOnClickPopLeftListener(this);


        //当前页面的上拉加载下拉刷新
        lv.setMode(PullToRefreshBase.Mode.PULL_FROM_END);//只有上拉
        lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {
                //这个里面没有下拉刷新
            }

            @Override
            //上拉刷新方法
            public void onPullUpToRefresh(PullToRefreshBase<ListView> pullToRefreshBase) {

                String urlUp = TheValues.FOOD_SEARCH_SECOND_UP_REFRESH_BEFORE + (page + 1) + "&order_asc=desc&q=" + UTF;
                Log.d("ResultFragment", urlUp);
                //开启帧动画
                if (!flag) {
                    animation.setVisibility(View.VISIBLE);
                }
                //获取到AnimationDrawable动画对象
                animation.setImageResource(R.drawable.animation_food);
                final AnimationDrawable anim = (AnimationDrawable) animation.getDrawable();
                anim.start();

                GsonRequest<FoodResultBean> gsonRequest = new GsonRequest<FoodResultBean>(FoodResultBean.class, urlUp,
                        new Response.Listener<FoodResultBean>() {

                            @Override
                            public void onResponse(FoodResultBean response) {
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
                lv.onRefreshComplete();
                page++;
            }
        });
        //显示的从大到小排序和从小到大排序的点击事件
        llUpDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ResultFragment1", "点击了123");
                if (isClick) {
                    tvUpDown.setText("由低到高");
                    imgUpDown.setImageResource(R.mipmap.ic_arrow_up_selected);
//                    urlToUp = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q="+UTF;
                    urlToUp = TheValues.FOOD_SEARCH_SECOND_DOWN_TO_UP+UTF;
                    Log.d("ResultFragment22", UTF+"----------");
                    initSearchListView(urlToUp);
                    isClick = !isClick;
                } else {
                    tvUpDown.setText("由高到低");
                    imgUpDown.setImageResource(R.mipmap.ic_arrow_down_selected);
//                    urlToDown = "http://food.boohee.com/fb/v1/search?page=1&order_asc=asc&q="+UTF;
                    urlToDown = TheValues.FOOD_SEARCH_SECOND_UP_TO_DOWN+UTF;
                    initSearchListView(urlToDown);
                    isClick = !isClick;

                }

            }
        });
    }

    //listView的网络请求方法
    private void initSearchListView(String url) {
        //开启帧动画
        if (!flag) {
            animation.setVisibility(View.VISIBLE);
        }
        //获取到AnimationDrawable动画对象
        animation.setImageResource(R.drawable.animation_food);
        final AnimationDrawable anim = (AnimationDrawable) animation.getDrawable();
        anim.start();

        GsonRequest<FoodResultBean> gsonRequest = new GsonRequest<FoodResultBean>(FoodResultBean.class, url,
                new Response.Listener<FoodResultBean>() {

                    @Override
                    public void onResponse(FoodResultBean response) {
                        adapter.setFoodResultBean(response);
                        lv.setAdapter(adapter);
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

    @Override
    public void onClickPopLeft(String name, String code) {
        tvPop.setText(name);
        //左侧的pop的点击事件
        Log.d("ResultFragment123", name);
        Log.d("ResultFragment123", code);
        try {
            nameStr = URLEncoder.encode(name, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (code.equals("common")) {
            if (llCheckBox.getVisibility() == View.GONE){
                llCheckBox.setVisibility(View.VISIBLE);
                llUpDown.setVisibility(View.GONE);
            }

//            url = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=" + UTF;
            url = TheValues.FOOD_SEARCH_SECOND_LV_BEFORE + UTF;
        } else {
            url = TheValues.FOOD_SEARCH_SECOND_LV_BEFORE + nameStr + "&order_by=" + code;
            llCheckBox.setVisibility(View.GONE);
            initSearchListView(url);
            if (llUpDown.getVisibility() == View.GONE) {
                llUpDown.setVisibility(View.VISIBLE);
                tvUpDown.setText("由高到低");
                imgUpDown.setImageResource(R.mipmap.ic_arrow_down_selected);
//                initSearchListView(urlToUp);
                isClick = !isClick;
            }
        }
        popUpWindow.dismiss();
    }
}
