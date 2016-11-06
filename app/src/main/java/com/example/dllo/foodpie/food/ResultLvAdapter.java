package com.example.dllo.foodpie.food;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.FoodResultBean;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/11/5.
 */
public class ResultLvAdapter extends BaseAdapter {
    private Context context;

    private FoodResultBean foodResultBean;

    public void setFoodResultBean(FoodResultBean foodResultBean) {
        this.foodResultBean = foodResultBean;
        notifyDataSetChanged();
    }

    public ResultLvAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return foodResultBean.getItems() == null ? 0 : foodResultBean.getItems().size();
    }

    @Override
    public Object getItem(int position) {
        return foodResultBean.getItems().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_food_result, parent, false);
            viewHolder = new MyViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(foodResultBean.getItems().get(position).getName());
        viewHolder.calory.setText(foodResultBean.getItems().get(position).getCalory());

        String imgUrlThumb = foodResultBean.getItems().get(position).getThumb_image_url();
        VolleySingleton.getInstance().getImage(imgUrlThumb, viewHolder.thumb);

        int num = foodResultBean.getItems().get(position).getHealth_light();
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
            thumb = (ImageView) convertView.findViewById(R.id.img_food_result_thumb);
            name = (TextView) convertView.findViewById(R.id.tv_food_result_name);
            calory = (TextView) convertView.findViewById(R.id.tv_food_result_calory);
            point = (ImageView) convertView.findViewById(R.id.img_food_result_point);
        }
    }
}
