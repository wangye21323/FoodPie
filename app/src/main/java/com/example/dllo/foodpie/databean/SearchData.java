package com.example.dllo.foodpie.databean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/11/8.
 */
public class SearchData {
    // 指定自增，每个对象需要有一个主键//3
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
