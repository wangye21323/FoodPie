package com.example.dllo.foodpie.food;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.dllo.foodpie.R;
import com.example.dllo.foodpie.base.CommonViewHolder;
import com.example.dllo.foodpie.databean.FoodAnalyzeBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dllo on 16/11/10.
 */
public class AnalyzeRvAdapter extends RecyclerView.Adapter<CommonViewHolder> {
    //    private FoodAnalyzeBean foodAnalyzeBean;
    private List<AnalyzeFoodBean> foodsBeen = new ArrayList<>();

    public void setFoodAnalyzeBean(String local, FoodAnalyzeBean foodAnalyzeBean) {
        for (FoodAnalyzeBean.NutritionBean nutritionBean : foodAnalyzeBean.getNutrition()) {
            //循环传入的数据, 取出元素的名字
            String nutritionName = nutritionBean.getName();
            AnalyzeFoodBean analyzeFoodBean = null;
            for (AnalyzeFoodBean bean : foodsBeen) {
                if (bean.getNutritionName().equals(nutritionName)) {
                    //数据类中有该元素
                    analyzeFoodBean = bean;
                    break;
                }
            }
            if (analyzeFoodBean == null) {
                //如果数据类中没有这个元素
                analyzeFoodBean = new AnalyzeFoodBean();
                analyzeFoodBean.setNutritionName(nutritionBean.getName());
                foodsBeen.add(analyzeFoodBean);//把新元素加入到数据类中
            }
            //现在数据类中一定有这个元素了
            analyzeFoodBean.addNutritionBeanByLocation(nutritionBean, local);
        }
        //排序
        Collections.sort(foodsBeen);
        notifyDataSetChanged();
    }

    public void clearLocation(String location) {
        for (int i = foodsBeen.size() - 1; i >= 0; i--) {
            AnalyzeFoodBean analyzeFoodBean = foodsBeen.get(i);
            analyzeFoodBean.clearLocation(location);

            if (analyzeFoodBean.getLeft() == null && analyzeFoodBean.getRight() == null) {
                foodsBeen.remove(i);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CommonViewHolder.getViewHolder(parent, R.layout.item_analyze);
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        AnalyzeFoodBean analyzeFoodBean = foodsBeen.get(position);
        holder.setText(R.id.item_analyze_center, analyzeFoodBean.getNutritionName());
        if (analyzeFoodBean.getLeft() != null) {
            holder.setText(R.id.item_analyze_left, analyzeFoodBean.getLeft().getValue() + analyzeFoodBean.getLeft().getUnit());
        } else {
            holder.setText(R.id.item_analyze_left, "--");
        }
        FoodAnalyzeBean.NutritionBean right = analyzeFoodBean.getRight();
        if (right != null) {
            holder.setText(R.id.item_analyze_right, right.getValue() + right.getUnit());
        } else {
            holder.setText(R.id.item_analyze_right, "--");
        }
    }

    @Override
    public int getItemCount() {
        return foodsBeen == null ? 0 : foodsBeen.size();
    }
}

class AnalyzeFoodBean implements Comparable<AnalyzeFoodBean> {

    private String nutritionName;
    private FoodAnalyzeBean.NutritionBean left;
    private FoodAnalyzeBean.NutritionBean right;

    public void addNutritionBeanByLocation(FoodAnalyzeBean.NutritionBean bean, String location) {
        if ("Left".equals(location)) {
            left = bean;
        } else {
            right = bean;
        }
    }

    public void clearLocation(String location) {
        if ("Left".equals(location)) {
            left = null;
        } else {
            right = null;
        }
    }


    public FoodAnalyzeBean.NutritionBean getLeft() {
        return left;
    }

    public void setLeft(FoodAnalyzeBean.NutritionBean left) {
        this.left = left;
    }

    public String getNutritionName() {
        return nutritionName;
    }

    public void setNutritionName(String nutritionName) {
        this.nutritionName = nutritionName;
    }

    public FoodAnalyzeBean.NutritionBean getRight() {
        return right;
    }

    public void setRight(FoodAnalyzeBean.NutritionBean right) {
        this.right = right;
    }

    @Override
    public int compareTo(AnalyzeFoodBean another) {
        if (this.left != null && this.right != null) {
            if (another.left == null || another.right == null) {
                return 1;
            }
            return nutritionName.compareTo(another.nutritionName);
        }
        return 0;
    }
}
