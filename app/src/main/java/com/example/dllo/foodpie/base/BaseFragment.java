package com.example.dllo.foodpie.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.foodpie.R;

/**
 * Created by dllo on 16/10/21.
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(getLayout() == 0){
            // 如果 Fragment没有指定布局
            // 默认 就加载一个空布局, 防止程序崩溃
            return inflater.inflate(R.layout.null_layout,container,false);
        }

        return inflater.inflate(getLayout(),container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected  <T extends View> T bindView(int id){
        return (T) getView().findViewById(id);
    }

    // 指定在哪个View里findViewById
    protected  <T extends View> T bindView(View view,int id){
        return (T) view.findViewById(id);
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int getLayout();
}
