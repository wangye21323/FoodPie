package com.example.dllo.foodpie.food;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.BaseActivity;
import com.example.dllo.foodpie.databean.EventText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by dllo on 16/11/5.
 */
public class SearchActivity extends BaseActivity implements View.OnClickListener {


    private ImageButton iBtnBack;
    private EditText edtInput;
    private ImageButton iBtnSearch;
    private FrameLayout fram;


    @Override
    protected int getLayout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews() {
        fram = bindView(R.id.frame_search);
        iBtnBack = bindView(R.id.iBtn_search_back);
        iBtnSearch = bindView(R.id.iBtn_search_search);
        edtInput = bindView(R.id.edt_food_search);
        //详情界面的额点击返回
        iBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //首次进入是搜索fragment
        InputFragment inputFragment = new InputFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_search, inputFragment);
        transaction.commit();

        iBtnSearch.setOnClickListener(this);
    }

    @Override
    protected void initDate() {
        EventBus.getDefault().register(this);
    }

    @Override
    //搜索按钮
    public void onClick(View v) {
        if (edtInput.getText().toString().isEmpty()){
            Toast.makeText(this, "输入不能为空哦", Toast.LENGTH_SHORT).show();
        }else{
            ResultFragment resultFragment = new ResultFragment();
            String name = edtInput.getText().toString();
            //bundle最好放在提交的上边, 防止错误
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            resultFragment.setArguments(bundle);
            FragmentManager manager1 = getSupportFragmentManager();
            FragmentTransaction transaction1 = manager1.beginTransaction();
            transaction1.replace(R.id.frame_search, resultFragment);
            transaction1.commit();


        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventSetText(EventText eventText){
        edtInput.setText(eventText.getText());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
