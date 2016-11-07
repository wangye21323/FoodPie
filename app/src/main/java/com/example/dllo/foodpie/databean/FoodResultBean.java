package com.example.dllo.foodpie.databean;

import java.util.List;

/**
 * Created by dllo on 16/11/5.
 */
public class FoodResultBean {

    /**
     * page : 1
     * total_pages : 16
     * tags : [{"type":"tags","name":"粗杂粮类及制品"},{"type":"tags","name":"薯芋类"},{"type":"tags","name":"早餐"},{"type":"tags","name":"晚餐"},{"type":"tags","name":"加餐"},{"type":"tags","name":"主食其他"},{"type":"tags","name":"全年"}]
     * items : [{"id":92,"code":"ganshu_hongxin","name":"红薯","thumb_image_url":"http://s.boohee.cn/house/upload_food/2016/9/9/mid_photo_url_11205d343645490285957786bbdceadf.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"102","type":"food"},{"id":17642,"code":"hongshuzhou","name":"红薯粥","thumb_image_url":"http://s.boohee.cn/house/upload_food/2008/10/20/69689_1224475999.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"64","type":"food"},{"id":93,"code":"ganshupian","name":"甘薯片","thumb_image_url":"http://s.boohee.cn/house/food_mid/mid_photo_201022493.jpg","is_liquid":false,"health_light":3,"weight":"100","calory":"344","type":"food"},{"id":94,"code":"ganshufen","name":"甘薯粉","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_1169435595947.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"336","type":"food"},{"id":17767,"code":"hongshufan","name":"红薯饭","thumb_image_url":"http://s.boohee.cn/house/food_mid/mid_photo_201512115111217767.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"91","type":"food"},{"id":359,"code":"baishuye","name":"红薯叶","thumb_image_url":"http://s.boohee.cn/house/food_mid/m_1160992903125.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"60","type":"food"},{"id":19971,"code":"hongshugao","name":"红薯糕","thumb_image_url":"http://s.boohee.cn/house/upload_food/2009/7/21/257637_1248169151mid.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"93","type":"food"},{"id":16974,"code":"hongshutangbing","name":"红薯糖饼","thumb_image_url":"http://s.boohee.cn/house/food_mid/mid_photo_201151815503116974.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"256","type":"food"},{"id":91,"code":"ganshu_baixin","name":"白薯","thumb_image_url":"http://s.boohee.cn/house/food_mid/mid_photo_201512617183491.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"106","type":"food"},{"id":18375,"code":"hongshuzhou_xi","name":"红薯粥（稀）","thumb_image_url":"http://s.boohee.cn/house/upload_food/2009/1/11/129336_1231665825mid.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"73","type":"food"},{"id":18418,"code":"mishaohongshu","name":"蜜烧红薯","thumb_image_url":"http://s.boohee.cn/house/upload_food/2009/1/16/96504_1232035446mid.jpg","is_liquid":false,"health_light":2,"weight":"100","calory":"277","type":"food"},{"id":11771,"code":"zhimahongshubing","name":"芝麻红薯饼","thumb_image_url":"http://s.boohee.cn/house/upload_food/2008/12/9/user_113819_1228807967.jpg","is_liquid":false,"health_light":2,"weight":"100","calory":"219","type":"food"},{"id":20555,"code":"xiangchenghongshupian","name":"香橙红薯片","thumb_image_url":"http://s.boohee.cn/house/upload_food/2009/10/7/156362_1254918003mid.jpg","is_liquid":false,"health_light":2,"weight":"100","calory":"207","type":"food"},{"id":17705,"code":"ganshuyanmaizhou","name":"甘薯燕麦粥","thumb_image_url":"http://s.boohee.cn/house/upload_food/2008/10/30/153113_1225324135mid.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"44","type":"food"},{"id":80388,"code":"hongshupai","name":"红薯派","thumb_image_url":"http://s.boohee.cn/house/upload_food/2014/5/17/2019409_1400288118mid.jpg","is_liquid":false,"health_light":2,"weight":"100","calory":"214","type":"food"},{"id":94670,"code":"hongshusu","name":"红薯酥","thumb_image_url":"http://s.boohee.cn/house/upload_food/2014/10/23/452803_1414057623mid.jpg","is_liquid":false,"health_light":3,"weight":"100","calory":"347","type":"food"},{"id":17152,"code":"suannaihongshuni","name":"酸奶红薯泥","thumb_image_url":"http://s.boohee.cn/house/upload_food/2008/8/4/100027_1217810636mid.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"82","type":"food"},{"id":36680,"code":"kaohongshu2","name":"烤红薯","thumb_image_url":"http://s.boohee.cn/house/upload_food/2012/10/18/1510332_1350522420mid.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"131","type":"food"},{"id":100186,"code":"hongshupian","name":"红薯片","thumb_image_url":"http://s.boohee.cn/house/upload_food/2015/2/12/1755110_1423711907mid.jpg","is_liquid":false,"health_light":3,"weight":"100","calory":"540","type":"food"},{"id":22384,"code":"ASDAhongshu","name":"ASDA红薯","thumb_image_url":"http://s.boohee.cn/house/upload_food/2010/5/12/526638_1273605900mid.jpg","is_liquid":false,"health_light":1,"weight":"100","calory":"114","type":"food"}]
     */

    private int page;
    private int total_pages;
    /**
     * type : tags
     * name : 粗杂粮类及制品
     */

    private List<TagsBean> tags;
    /**
     * id : 92
     * code : ganshu_hongxin
     * name : 红薯
     * thumb_image_url : http://s.boohee.cn/house/upload_food/2016/9/9/mid_photo_url_11205d343645490285957786bbdceadf.jpg
     * is_liquid : false
     * health_light : 1
     * weight : 100
     * calory : 102
     * type : food
     */

    private List<ItemsBean> items;

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

    public List<TagsBean> getTags() {
        return tags;
    }

    public void setTags(List<TagsBean> tags) {
        this.tags = tags;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public void addData(List<ItemsBean> items) {
        this.items.addAll(items);
    }

    public static class TagsBean {
        private String type;
        private String name;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class ItemsBean {
        private int id;
        private String code;
        private String name;
        private String thumb_image_url;
        private boolean is_liquid;
        private int health_light;
        private String weight;
        private String calory;
        private String type;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
