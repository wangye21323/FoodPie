package com.example.dllo.foodpie.databean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/11/15.
 */
public class FoodAllDbBean {
    // 指定自增，每个对象需要有一个主键//3
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    private String large_image_url;
    private String name;
    private String calory;

    public String getCalory() {
        return calory;
    }

    public void setCalory(String calory) {
        this.calory = calory;
    }


    public String getLarge_image_url() {
        return large_image_url;
    }

    public void setLarge_image_url(String large_image_url) {
        this.large_image_url = large_image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
