package com.example.dllo.foodpie.food;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
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
    private ImageButton iBtnClear;
    private boolean isClick = false;
    private int length;



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
        iBtnClear = bindView(R.id.iBtn_search_clear);

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
        //清除按钮的点击, 没有字的时候, 按钮是隐藏的
        iBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtInput.getText().clear();
            }
        });
    }

    @Override
    protected void initDate() {
        //注册eventBus
        EventBus.getDefault().register(this);

        //获取到editText的变化状态
        edtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                length = edtInput.getText().toString().length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当字数改变时, 切换界面
                if (isClick && edtInput.getText().toString().length() < length){
                    InputFragment fragment = new InputFragment();
                    FragmentManager manager = getSupportFragmentManager();

                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.frame_search, fragment);
                    transaction.commit();
                    isClick = false;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtInput.getText().toString().length() > 0){
                    iBtnClear.setVisibility(View.VISIBLE);
                }else{
                    iBtnClear.setVisibility(View.GONE);
                }
            }
        });


    }

    @Override
    //搜索按钮点击事件
    public void onClick(View v) {
        isClick = true;
        if (edtInput.getText().toString().isEmpty()){
            Toast.makeText(this, "输入不能为空哦", Toast.LENGTH_SHORT).show();
        }else{
            ResultFragment resultFragment = new ResultFragment();
            String name = edtInput.getText().toString();
            //bundle最好放在提交的上边, 防止错误
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            resultFragment.setArguments(bundle);
            //点击切换
            FragmentManager manager1 = getSupportFragmentManager();
            FragmentTransaction transaction1 = manager1.beginTransaction();
            transaction1.replace(R.id.frame_search, resultFragment);
            transaction1.commit();


        }
    }

    //eventBus 接收第一个界面点击常用时带过来的值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventSetText(EventText eventText){
        isClick = true;
        edtInput.setText(eventText.getText());
        edtInput.setSelection(eventText.getText().length());
    }

    @Override
    //解绑eventBus
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
