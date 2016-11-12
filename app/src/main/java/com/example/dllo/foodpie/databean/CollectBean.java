package com.example.dllo.foodpie.databean;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by dllo on 16/11/12.
 */
public class CollectBean {
    // 指定自增，每个对象需要有一个主键//3
    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private int id;
    String title;
    String url;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
