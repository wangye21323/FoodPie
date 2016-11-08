package com.example.dllo.foodpie.food;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.EventText;
import com.example.dllo.foodpie.databean.HistoryBean;
import com.example.dllo.foodpie.databean.SearchBean;
import com.example.dllo.foodpie.databean.SearchData;
import com.example.dllo.foodpie.dbtool.DBTool;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/5.
 */
public class InputFragment extends BaseFragment implements SearchOnClickListener {
    private ImageButton iBtnBack;
    private EditText edtInput;
    private ImageButton iBtnSearch;
    private RecyclerView rvSearch;
    private SearchRvAdapter adapter;
    private FrameLayout fram;
    private RelativeLayout rvDel;
    private LinearLayout llHistory;
    private ListView lvHistory;

    @Override
    protected int getLayout() {
        return R.layout.fragment_input;
    }

    @Override
    protected void initView() {
        rvSearch = bindView(R.id.rv_food_search_all);
        rvDel = bindView(R.id.rv_food_search_del);
        adapter = new SearchRvAdapter(MyApp.getContext());
        llHistory = bindView(R.id.ll_food_search_history);
        lvHistory = bindView(R.id.lv_food_search_near);
        GridLayoutManager manager = new GridLayoutManager(MyApp.getContext(),2);
        rvSearch.setLayoutManager(manager);
        rvSearch.setAdapter(adapter);
    }


    @Override
    //搜索界面的大家都在搜的网络请求
    protected void initData() {

        getHistory();

//        String url = "http://food.boohee.com/fb/v1/keywords?token=&user_key=&app_version=2.6";
        GsonRequest<SearchBean> gsonRequest = new GsonRequest<SearchBean>(SearchBean.class, TheValues.FOOD_SEARCH_FIRST_HOT,
                new Response.Listener<SearchBean>() {
                    @Override
                    public void onResponse(SearchBean response) {
                        //请求成功数据
                        adapter.setSearchBean(response);


                        RecyclerViewHeader header = bindView(R.id.rv_header);
                        header.attachTo(rvSearch, true);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
        adapter.setOnClickListener(this);

        //历史记录点击清除按钮
        rvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rvDel.getVisibility()==View.VISIBLE){
                    DBTool.getInstance().deleteSearchData(SearchData.class);
                    llHistory.setVisibility(View.GONE);
                }
            }
        });
    }

    //获取历史记录
    private void getHistory() {
        //当点击大家都在搜其中一项时, 把历史显现出来
        llHistory.setVisibility(View.VISIBLE);
        final ArrayList<HistoryBean> been = new ArrayList<>();
        DBTool.getInstance().queryAllSearchData(new DBTool.OnQueryListener() {
            @Override
            public void onQuery(List<SearchData> person) {
                for (int i = 0; i < person.size(); i++) {
                    HistoryBean historyBean = new HistoryBean(person.get(i).getName());
                    been.add(historyBean);
                }
                HistoryLvAdapter adapter = new HistoryLvAdapter(MyApp.getContext());
                adapter.setHistoryBean(been);
                lvHistory.setAdapter(adapter);
            }
        });

    }

    @Override
    public void onClickSearch(String name) {
        //当点击搜索的按钮时, 传数据
        ResultFragment fragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Title", name);
        fragment.setArguments(bundle);

        //点击搜索, 替换当前的fragment
        FragmentManager manager1 = getFragmentManager();
        FragmentTransaction transaction1 = manager1.beginTransaction();
        transaction1.replace(R.id.frame_search, fragment);
        transaction1.commit();

        //第一个界面的大家都在搜的点击给activity上的edittext传值
        EventText eventText = new EventText();
        eventText.setText(name);
        EventBus.getDefault().post(eventText);


        //把输入的name存入数据库
        SearchData data = new SearchData();
        data.setName(name);
        DBTool.getInstance().insertSearchData(data);

    }


}
