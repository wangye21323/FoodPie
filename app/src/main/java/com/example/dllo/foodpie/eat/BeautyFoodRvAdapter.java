package com.example.dllo.foodpie.eat;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.dllo.foodpie.databean.BeautyFoodBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/26.
 */
public class BeautyFoodRvAdapter extends RecyclerView.Adapter<BeautyFoodRvAdapter.MyViewHolder>{
    private ArrayList<BeautyFoodBean> arrayList;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
