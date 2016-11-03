package com.example.dllo.foodpie.databean;

/**
 * Created by dllo on 16/11/3.
 */
public class FoodDescriptionRightPopBean {
    String name;
    int id;

    public FoodDescriptionRightPopBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
