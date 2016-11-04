package com.example.dllo.foodpie.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.FoodDescriptionRightPopBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/3.
 */
public class PopRightLvAdapter extends BaseAdapter{
    private ArrayList<FoodDescriptionRightPopBean> foodDescriptionRightPopBean;
    private Context context;
    private OnClickPopRight onClickPopRight;

    public void setOnClickPopRight(OnClickPopRight onClickPopRight) {
        this.onClickPopRight = onClickPopRight;
    }

    public void setFoodDescriptionRightPopBean(ArrayList<FoodDescriptionRightPopBean> foodDescriptionRightPopBean) {
        this.foodDescriptionRightPopBean = foodDescriptionRightPopBean;
        notifyDataSetChanged();
    }

    public PopRightLvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return foodDescriptionRightPopBean.size();
    }

    @Override
    public FoodDescriptionRightPopBean getItem(int position) {
        return foodDescriptionRightPopBean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food_description_popwindow_right, null);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(foodDescriptionRightPopBean.get(position).getName());
        viewHolder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPopRight.onClickRight(foodDescriptionRightPopBean.get(position).getId(), foodDescriptionRightPopBean.get(position).getName());
            }
        });

        return convertView;
    }

    private class MyViewHolder {

        private TextView name;
        private LinearLayout ll;
        private TextView id;

        public MyViewHolder(View convertView) {
            name = (TextView) convertView.findViewById(R.id.tv_food_description_pop_right);
            ll = (LinearLayout) convertView.findViewById(R.id.ll_food_description_right_pop);
        }
    }
}
