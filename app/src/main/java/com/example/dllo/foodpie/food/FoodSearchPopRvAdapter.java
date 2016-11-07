package com.example.dllo.foodpie.food;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.databean.FoodSearchPopBean;

/**
 * Created by dllo on 16/11/1.
 */
public class FoodSearchPopRvAdapter extends RecyclerView.Adapter<FoodSearchPopRvAdapter.MyPopViewHolder> {

//    private FoodDescriptionPopBean foodDescriptionPopBean;
    private FoodSearchPopBean foodSearchPopBean;
    private Context context;
    private MyPopViewHolder viewHolder;
    private OnClickPopLeftListener onClickPopLeftListener;

    public FoodSearchPopRvAdapter(Context context) {
        this.context = context;
    }

    public void setOnClickPopLeftListener(OnClickPopLeftListener onClickPopLeftListener) {
        this.onClickPopLeftListener = onClickPopLeftListener;
    }

    public void setFoodSearchPopBean(FoodSearchPopBean foodSearchPopBean) {
        this.foodSearchPopBean = foodSearchPopBean;
        notifyDataSetChanged();
    }


    @Override
    public MyPopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food_description_pop, parent, false);
        viewHolder = new MyPopViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyPopViewHolder holder, final int position) {
        holder.name.setText("全部");
        holder.name.setText(foodSearchPopBean.getTypes().get(position).getName());
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPopLeftListener.onClickPopLeft(foodSearchPopBean.getTypes().get(position).getName(), foodSearchPopBean.getTypes().get(position).getCode());
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodSearchPopBean.getTypes() == null ? 0 : foodSearchPopBean.getTypes().size();
    }

    public class MyPopViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private final LinearLayout ll;

        public MyPopViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_food_description_pop_text);
            ll = (LinearLayout) itemView.findViewById(R.id.ll_food_description_line);
        }
    }
}

