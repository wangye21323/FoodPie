package com.example.dllo.foodpie.databean;

/**
 * Created by dllo on 16/11/8.
 */
public class HistoryBean {
    String name;

    public HistoryBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof HistoryBean){
            HistoryBean other = (HistoryBean) o;
            return this.getName().equals(other.getName());
        }
        return false;

    }
}
