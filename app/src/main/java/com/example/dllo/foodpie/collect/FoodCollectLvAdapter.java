package com.example.dllo.foodpie.collect;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.CommonViewHolder;
import com.example.dllo.foodpie.databean.FoodCollectBean;
import com.example.dllo.foodpie.food.FoodAllActivity;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/16.
 */
public class FoodCollectLvAdapter extends BaseAdapter{
    private ArrayList<FoodCollectBean> foodCollectBeen;
    private Context context;

    public FoodCollectLvAdapter(Context context) {
        this.context = context;
    }

    public void setFoodCollectBeen(ArrayList<FoodCollectBean> foodCollectBeen) {
        this.foodCollectBeen = foodCollectBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return foodCollectBeen == null ? 0 : foodCollectBeen.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(convertView, parent, R.layout.item_food_collect);
        commonViewHolder.setText(R.id.tv_collect_article_name, foodCollectBeen.get(position).getName()).
                setText(R.id.tv_collect_article_weight, foodCollectBeen.get(position).getWeight()).
                setImage(R.id.img_collect_food_icon, foodCollectBeen.get(position).getUrl()).setViewClick(R.id.rv_food_collect_item, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FoodAllActivity.class);
                intent.putExtra("Code",foodCollectBeen.get(position).getCode());
                context.startActivity(intent);
            }
        });
        return commonViewHolder.getItemView();
    }
}
