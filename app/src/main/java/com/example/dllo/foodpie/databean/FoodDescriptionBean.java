package com.example.dllo.foodpie.databean;

import java.util.List;

/**
 * Created by dllo on 16/10/31.
 */
public class FoodDescriptionBean {

    /**
     * page : 1
     * total_pages : 649
     * foods : []
     */

    private int page;
    private int total_pages;
    /**
     * id : 56
     * code : mifan_zheng
     * name : 米饭
     * thumb_image_url : http://s.boohee.cn/house/food_mid/mid_photo_201512613144756.jpg
     * is_liquid : false
     * health_light : 1
     * weight : 100
     * calory : 116
     * fat : 0.3
     * protein : 2.6
     * fiber_dietary : 0.3
     * carbohydrate : 25.6
     * vitamin_a :
     * thiamine : 0.0
     * lactoflavin : 0.0
     * vitamin_c :
     * vitamin_e :
     * niacin : 1.9
     * natrium : 2.5
     * calcium : 7.0
     * iron : 1.3
     * kalium : 30.0
     * iodine :
     * zinc : 0.9
     * selenium : 0.4
     * magnesium : 15.0
     * copper : 0.1
     * manganese : 0.6
     * cholesterol :
     */

    private List<FoodsBean> foods;

    public void addData(List<FoodsBean> foods){
        this.foods.addAll(foods);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<FoodsBean> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodsBean> foods) {
        this.foods = foods;
    }

    public static class FoodsBean {
        private int id;
        private String code;
        private String name;
        private String thumb_image_url;
        private boolean is_liquid;
        private int health_light;
        private String weight;
        private String calory;
        private String fat;
        private String protein;
        private String fiber_dietary;
        private String carbohydrate;
        private String vitamin_a;
        private String thiamine;
        private String lactoflavin;
        private String vitamin_c;
        private String vitamin_e;
        private String niacin;
        private String natrium;
        private String calcium;
        private String iron;
        private String kalium;
        private String iodine;
        private String zinc;
        private String selenium;
        private String magnesium;
        private String copper;
        private String manganese;
        private String cholesterol;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getThumb_image_url() {
            return thumb_image_url;
        }

        public void setThumb_image_url(String thumb_image_url) {
            this.thumb_image_url = thumb_image_url;
        }

        public boolean isIs_liquid() {
            return is_liquid;
        }

        public void setIs_liquid(boolean is_liquid) {
            this.is_liquid = is_liquid;
        }

        public int getHealth_light() {
            return health_light;
        }

        public void setHealth_light(int health_light) {
            this.health_light = health_light;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
            this.fat = fat;
        }

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
            this.protein = protein;
        }

        public String getFiber_dietary() {
            return fiber_dietary;
        }

        public void setFiber_dietary(String fiber_dietary) {
            this.fiber_dietary = fiber_dietary;
        }

        public String getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(String carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public String getVitamin_a() {
            return vitamin_a;
        }

        public void setVitamin_a(String vitamin_a) {
            this.vitamin_a = vitamin_a;
        }

        public String getThiamine() {
            return thiamine;
        }

        public void setThiamine(String thiamine) {
            this.thiamine = thiamine;
        }

        public String getLactoflavin() {
            return lactoflavin;
        }

        public void setLactoflavin(String lactoflavin) {
            this.lactoflavin = lactoflavin;
        }

        public String getVitamin_c() {
            return vitamin_c;
        }

        public void setVitamin_c(String vitamin_c) {
            this.vitamin_c = vitamin_c;
        }

        public String getVitamin_e() {
            return vitamin_e;
        }

        public void setVitamin_e(String vitamin_e) {
            this.vitamin_e = vitamin_e;
        }

        public String getNiacin() {
            return niacin;
        }

        public void setNiacin(String niacin) {
            this.niacin = niacin;
        }

        public String getNatrium() {
            return natrium;
        }

        public void setNatrium(String natrium) {
            this.natrium = natrium;
        }

        public String getCalcium() {
            return calcium;
        }

        public void setCalcium(String calcium) {
            this.calcium = calcium;
        }

        public String getIron() {
            return iron;
        }

        public void setIron(String iron) {
            this.iron = iron;
        }

        public String getKalium() {
            return kalium;
        }

        public void setKalium(String kalium) {
            this.kalium = kalium;
        }

        public String getIodine() {
            return iodine;
        }

        public void setIodine(String iodine) {
            this.iodine = iodine;
        }

        public String getZinc() {
            return zinc;
        }

        public void setZinc(String zinc) {
            this.zinc = zinc;
        }

        public String getSelenium() {
            return selenium;
        }

        public void setSelenium(String selenium) {
            this.selenium = selenium;
        }

        public String getMagnesium() {
            return magnesium;
        }

        public void setMagnesium(String magnesium) {
            this.magnesium = magnesium;
        }

        public String getCopper() {
            return copper;
        }

        public void setCopper(String copper) {
            this.copper = copper;
        }

        public String getManganese() {
            return manganese;
        }

        public void setManganese(String manganese) {
            this.manganese = manganese;
        }

        public String getCholesterol() {
            return cholesterol;
        }

        public void setCholesterol(String cholesterol) {
            this.cholesterol = cholesterol;
        }
    }
}
