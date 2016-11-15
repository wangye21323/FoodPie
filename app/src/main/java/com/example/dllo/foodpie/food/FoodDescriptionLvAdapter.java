package com.example.dllo.foodpie.food;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.FoodDescriptionBean;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/31.
 */
public class FoodDescriptionLvAdapter extends BaseAdapter {
    private FoodDescriptionBean foodDescriptionBean;
    private Context context;

    public void setFoodDescriptionBean(FoodDescriptionBean foodDescriptionBean) {
        this.foodDescriptionBean = foodDescriptionBean;
        Log.d("FoodDescriptionLvAdapte", "刷新了");
        notifyDataSetChanged();
    }

    public void addBeanData(FoodDescriptionBean foodDescriptionBean){
        this.foodDescriptionBean.addData(foodDescriptionBean.getFoods());
        notifyDataSetChanged();
    }


    public FoodDescriptionLvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return foodDescriptionBean.getFoods() == null ? 0 : foodDescriptionBean.getFoods().size();
    }

    public FoodDescriptionBean getItemMessage(){
        return foodDescriptionBean;
    }
    @Override
    public Object getItem(int position) {
        return foodDescriptionBean.getFoods().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food_description, parent, false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(foodDescriptionBean.getFoods().get(position).getName());
        viewHolder.calory.setText(foodDescriptionBean.getFoods().get(position).getCalory());



        String imgUrlThumb = foodDescriptionBean.getFoods().get(position).getThumb_image_url();
        VolleySingleton.getInstance().getImage(imgUrlThumb, viewHolder.thumb);

        int num = foodDescriptionBean.getFoods().get(position).getHealth_light();
        switch (num){
            case 1:
                viewHolder.point.setImageResource(R.mipmap.ic_food_light_green);
                break;
            case 2:
                viewHolder.point.setImageResource(R.mipmap.ic_food_light_yellow);
                break;
            case 3:
                viewHolder.point.setImageResource(R.mipmap.ic_food_light_red);
                break;
        }


        return convertView;
    }

    private class MyViewHolder {

        private ImageView thumb;
        private TextView name;
        private TextView calory;
        private ImageView point;

        public MyViewHolder(View convertView) {
            thumb = (ImageView) convertView.findViewById(R.id.img_food_description_thumb);
            name = (TextView) convertView.findViewById(R.id.tv_food_description_name);
            calory = (TextView) convertView.findViewById(R.id.tv_food_description_calory);
            point = (ImageView) convertView.findViewById(R.id.img_food_description_point);
        }
    }
}
