package com.example.dllo.foodpie.eat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.BeautyFoodBean;
import com.example.dllo.foodpie.web.VolleySingleton;

/**
 * Created by dllo on 16/10/26.
 */
public class BeautyFoodRvAdapter extends RecyclerView.Adapter<BeautyFoodRvAdapter.MyViewHolder>{
    private BeautyFoodBean beautyFoodBean;
    private Context context;
    private MyViewHolder viewHolder;

    public BeautyFoodRvAdapter(Context context) {
        this.context = context;
    }

    public void setBeautyFoodBean(BeautyFoodBean beautyFoodBean) {
        this.beautyFoodBean = beautyFoodBean;
        notifyDataSetChanged();
    }

    public void addBeanData(BeautyFoodBean beautyFoodBean){
        this.beautyFoodBean.addData(beautyFoodBean.getFeeds());
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        //这个方法的返回值就是下边方法的参数viewType
        return beautyFoodBean.getFeeds().get(position).getContent_type();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 1:
                View view = LayoutInflater.from(context).inflate(R.layout.eat_item_beautyfood, parent, false);
                viewHolder = new MyViewHolder(view);
                break;
            case 2:
                View view1 = LayoutInflater.from(context).inflate(R.layout.eat_item_beautyfood_else, parent, false);
                viewHolder = new MyViewHolder(view1);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        switch (beautyFoodBean.getFeeds().get(position).getContent_type()){
            case 1:
                holder.source.setText(beautyFoodBean.getFeeds().get(position).getSource());
                holder.title.setText(beautyFoodBean.getFeeds().get(position).getTitle());
                holder.tail.setText(beautyFoodBean.getFeeds().get(position).getTail());
                String imgUrl = beautyFoodBean.getFeeds().get(position).getImages().get(0);
                VolleySingleton.getInstance().getImage(imgUrl, holder.images);
                break;
            case 2:
                holder.sourceElse.setText(beautyFoodBean.getFeeds().get(position).getSource());
                holder.titleElse.setText(beautyFoodBean.getFeeds().get(position).getTitle());
                holder.tailElse.setText(beautyFoodBean.getFeeds().get(position).getTail());
                String imgUrlOne = beautyFoodBean.getFeeds().get(position).getImages().get(0);
                VolleySingleton.getInstance().getImage(imgUrlOne, holder.imagesOne);
                String imgUrlTwo = beautyFoodBean.getFeeds().get(position).getImages().get(1);
                VolleySingleton.getInstance().getImage(imgUrlTwo, holder.imagesTwo);
                String imgUrlThree = beautyFoodBean.getFeeds().get(position).getImages().get(2);
                VolleySingleton.getInstance().getImage(imgUrlThree, holder.imagesThree);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return beautyFoodBean.getFeeds() == null ? 0 : beautyFoodBean.getFeeds().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView source;
        private TextView title;
        private TextView tail;
        private ImageView images;
        private TextView sourceElse;
        private TextView titleElse;
        private TextView tailElse;
        private ImageView imagesOne;
        private ImageView imagesTwo;
        private ImageView imagesThree;

        public MyViewHolder(View itemView) {
            super(itemView);
            source = (TextView) itemView.findViewById(R.id.tv_eat_beautyfood_source);
            title = (TextView) itemView.findViewById(R.id.tv_eat_beautyfood_title);
            tail = (TextView) itemView.findViewById(R.id.tv_eat_beautyfood_tail);
            images = (ImageView) itemView.findViewById(R.id.img_eat_beautyfood_images);

            sourceElse = (TextView) itemView.findViewById(R.id.tv_eat_beauty_else_source);
            titleElse = (TextView) itemView.findViewById(R.id.tv_eat_beauty_else_title);
            tailElse = (TextView) itemView.findViewById(R.id.tv_eat_beauty_else_tail);
            imagesOne = (ImageView) itemView.findViewById(R.id.img_eat_beauty_else_images_one);
            imagesTwo = (ImageView) itemView.findViewById(R.id.img_eat_beauty_else_images_two);
            imagesThree = (ImageView) itemView.findViewById(R.id.img_eat_beauty_else_images_three);

        }
    }
}
