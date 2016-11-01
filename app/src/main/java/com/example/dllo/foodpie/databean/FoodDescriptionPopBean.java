package com.example.dllo.foodpie.databean;

import java.util.List;

/**
 * Created by dllo on 16/11/1.
 */
public class FoodDescriptionPopBean {

    /**
     * code : calory
     * name : 热量
     * index : 2
     */

    private List<TypesBean> types;

    public List<TypesBean> getTypes() {
        return types;
    }

    public void setTypes(List<TypesBean> types) {
        this.types = types;
    }

    public static class TypesBean {
        private String code;
        private String name;
        private String index;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIndex() {
            return index;
        }

        public void setIndex(String index) {
            this.index = index;
        }
    }
}
