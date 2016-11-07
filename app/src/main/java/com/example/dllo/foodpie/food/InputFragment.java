package com.example.dllo.foodpie.food;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseFragment;
import com.example.dllo.foodpie.base.MyApp;
import com.example.dllo.foodpie.databean.EventText;
import com.example.dllo.foodpie.databean.SearchBean;
import com.example.dllo.foodpie.web.GsonRequest;
import com.example.dllo.foodpie.web.TheValues;
import com.example.dllo.foodpie.web.VolleySingleton;

import org.greenrobot.eventbus.EventBus;

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
    @Override
    protected int getLayout() {
        return R.layout.fragment_input;
    }

    @Override
    protected void initView() {
        rvSearch = bindView(R.id.rv_food_search_all);
        adapter = new SearchRvAdapter(MyApp.getContext());
        rvSearch.setAdapter(adapter);
    }


    @Override
    //搜索界面的大家都在搜的网络请求
    protected void initData() {
//        String url = "http://food.boohee.com/fb/v1/keywords?token=&user_key=&app_version=2.6";
        GsonRequest<SearchBean> gsonRequest = new GsonRequest<SearchBean>(SearchBean.class, TheValues.FOOD_SEARCH_FIRST_HOT,
                new Response.Listener<SearchBean>() {
                    @Override
                    public void onResponse(SearchBean response) {
                        //请求成功数据
                        adapter.setSearchBean(response);
                        GridLayoutManager manager = new GridLayoutManager(MyApp.getContext(),2);
                        rvSearch.setLayoutManager(manager);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        VolleySingleton.getInstance().addRequest(gsonRequest);
        adapter.setOnClickListener(this);
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

        //第一个界面的额大家都在搜的点击给activity上的edittext传值
        EventText eventText = new EventText();
        eventText.setText(name);
        EventBus.getDefault().post(eventText);
    }


}
