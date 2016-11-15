package com.example.dllo.foodpie.databean;

import java.util.List;

/**
 * Created by dllo on 16/11/15.
 */
public class FoodAllBean {

    /**
     * id : 1537
     * code : doujiang_tian
     * name : 豆浆（甜）
     * calory : 33
     * weight : 100
     * health_light : 1
     * is_liquid : false
     * thumb_image_url : http://s.boohee.cn/house/food_mid/mid_photo_201524160471537.jpg
     * large_image_url : http://s.boohee.cn/house/food_big/big_photo201524160471537.jpg
     * uploader :
     * appraise : 豆浆中加入糖的吃法，较一般豆浆来说碳水化合物升高、热量升高，但总体来说差别不是很大，不可替代牛奶，减肥期间适量食用。
     * protein : 2.4
     * fat : 0.5
     * fiber_dietary : 0.0
     * carbohydrate : 4.9
     * gi :
     * gl :
     * compare : {"unit1":"块","target_name":"豆腐","target_image_url":"http://up.boohee.cn/house/u/food_library/compare/tofu.png","amount0":"1","unit0":"杯","amount1":"1.0"}
     * recipe : false
     * recipe_type : null
     * units : [{"unit_id":2480,"amount":"1.0","unit":"大杯","weight":"350.0","eat_weight":"350.0","calory":"115.5"},{"unit_id":2481,"amount":"1.0","unit":"大碗","weight":"450.0","eat_weight":"450.0","calory":"148.5"},{"unit_id":139,"amount":"1.0","unit":"杯","weight":"250.0","eat_weight":"250.0","calory":"82.5"}]
     * ingredient : {"calory":"33.0","protein":"2.4","fat":"0.5","carbohydrate":"4.9","fiber_dietary":"","vitamin_a":"","vitamin_c":"","vitamin_e":"","carotene":"","thiamine":"","lactoflavin":"","niacin":"","cholesterol":"","magnesium":"","calcium":"","iron":"","zinc":"","copper":"","manganese":"","kalium":"","phosphor":"","natrium":"","selenium":""}
     * lights : {"calory":"低热量","protein":"","carbohydrate":"","fat":"低脂肪","fiber_dietary":"","gi":"","gl":""}
     * comments_ct : 7
     * health_appraise : [{"health_mode":0,"show":1,"light":1,"appraise":"豆浆中加入糖的吃法，较一般豆浆来说碳水化合物升高、热量升高，但总体来说差别不是很大，不可替代牛奶，减肥期间适量食用。"},{"health_mode":1,"show":1,"light":1,"appraise":"豆浆中加入糖的吃法，较一般豆浆来说碳水化合物升高、热量升高，但总体来说差别不是很大，不可替代牛奶，减肥期间适量食用。"}]
     */

    private int id;
    private String code;
    private String name;
    private String calory;
    private String weight;
    private int health_light;
    private boolean is_liquid;
    private String thumb_image_url;
    private String large_image_url;
    private String uploader;
    private String appraise;
    private String protein;
    private String fat;
    private String fiber_dietary;
    private String carbohydrate;
    private String gi;
    private String gl;
    /**
     * unit1 : 块
     * target_name : 豆腐
     * target_image_url : http://up.boohee.cn/house/u/food_library/compare/tofu.png
     * amount0 : 1
     * unit0 : 杯
     * amount1 : 1.0
     */

    private CompareBean compare;
    private boolean recipe;
    private Object recipe_type;
    /**
     * calory : 33.0
     * protein : 2.4
     * fat : 0.5
     * carbohydrate : 4.9
     * fiber_dietary :
     * vitamin_a :
     * vitamin_c :
     * vitamin_e :
     * carotene :
     * thiamine :
     * lactoflavin :
     * niacin :
     * cholesterol :
     * magnesium :
     * calcium :
     * iron :
     * zinc :
     * copper :
     * manganese :
     * kalium :
     * phosphor :
     * natrium :
     * selenium :
     */

    private IngredientBean ingredient;
    /**
     * calory : 低热量
     * protein :
     * carbohydrate :
     * fat : 低脂肪
     * fiber_dietary :
     * gi :
     * gl :
     */

    private LightsBean lights;
    private int comments_ct;
    /**
     * unit_id : 2480
     * amount : 1.0
     * unit : 大杯
     * weight : 350.0
     * eat_weight : 350.0
     * calory : 115.5
     */

    private List<UnitsBean> units;
    /**
     * health_mode : 0
     * show : 1
     * light : 1
     * appraise : 豆浆中加入糖的吃法，较一般豆浆来说碳水化合物升高、热量升高，但总体来说差别不是很大，不可替代牛奶，减肥期间适量食用。
     */

    private List<HealthAppraiseBean> health_appraise;

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

    public String getCalory() {
        return calory;
    }

    public void setCalory(String calory) {
        this.calory = calory;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getHealth_light() {
        return health_light;
    }

    public void setHealth_light(int health_light) {
        this.health_light = health_light;
    }

    public boolean isIs_liquid() {
        return is_liquid;
    }

    public void setIs_liquid(boolean is_liquid) {
        this.is_liquid = is_liquid;
    }

    public String getThumb_image_url() {
        return thumb_image_url;
    }

    public void setThumb_image_url(String thumb_image_url) {
        this.thumb_image_url = thumb_image_url;
    }

    public String getLarge_image_url() {
        return large_image_url;
    }

    public void setLarge_image_url(String large_image_url) {
        this.large_image_url = large_image_url;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
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

    public String getGi() {
        return gi;
    }

    public void setGi(String gi) {
        this.gi = gi;
    }

    public String getGl() {
        return gl;
    }

    public void setGl(String gl) {
        this.gl = gl;
    }

    public CompareBean getCompare() {
        return compare;
    }

    public void setCompare(CompareBean compare) {
        this.compare = compare;
    }

    public boolean isRecipe() {
        return recipe;
    }

    public void setRecipe(boolean recipe) {
        this.recipe = recipe;
    }

    public Object getRecipe_type() {
        return recipe_type;
    }

    public void setRecipe_type(Object recipe_type) {
        this.recipe_type = recipe_type;
    }

    public IngredientBean getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientBean ingredient) {
        this.ingredient = ingredient;
    }

    public LightsBean getLights() {
        return lights;
    }

    public void setLights(LightsBean lights) {
        this.lights = lights;
    }

    public int getComments_ct() {
        return comments_ct;
    }

    public void setComments_ct(int comments_ct) {
        this.comments_ct = comments_ct;
    }

    public List<UnitsBean> getUnits() {
        return units;
    }

    public void setUnits(List<UnitsBean> units) {
        this.units = units;
    }

    public List<HealthAppraiseBean> getHealth_appraise() {
        return health_appraise;
    }

    public void setHealth_appraise(List<HealthAppraiseBean> health_appraise) {
        this.health_appraise = health_appraise;
    }

    public static class CompareBean {
        private String unit1;
        private String target_name;
        private String target_image_url;
        private String amount0;
        private String unit0;
        private String amount1;

        public String getUnit1() {
            return unit1;
        }

        public void setUnit1(String unit1) {
            this.unit1 = unit1;
        }

        public String getTarget_name() {
            return target_name;
        }

        public void setTarget_name(String target_name) {
            this.target_name = target_name;
        }

        public String getTarget_image_url() {
            return target_image_url;
        }

        public void setTarget_image_url(String target_image_url) {
            this.target_image_url = target_image_url;
        }

        public String getAmount0() {
            return amount0;
        }

        public void setAmount0(String amount0) {
            this.amount0 = amount0;
        }

        public String getUnit0() {
            return unit0;
        }

        public void setUnit0(String unit0) {
            this.unit0 = unit0;
        }

        public String getAmount1() {
            return amount1;
        }

        public void setAmount1(String amount1) {
            this.amount1 = amount1;
        }
    }

    public static class IngredientBean {
        private String calory;
        private String protein;
        private String fat;
        private String carbohydrate;
        private String fiber_dietary;
        private String vitamin_a;
        private String vitamin_c;
        private String vitamin_e;
        private String carotene;
        private String thiamine;
        private String lactoflavin;
        private String niacin;
        private String cholesterol;
        private String magnesium;
        private String calcium;
        private String iron;
        private String zinc;
        private String copper;
        private String manganese;
        private String kalium;
        private String phosphor;
        private String natrium;
        private String selenium;

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
            this.protein = protein;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
            this.fat = fat;
        }

        public String getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(String carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public String getFiber_dietary() {
            return fiber_dietary;
        }

        public void setFiber_dietary(String fiber_dietary) {
            this.fiber_dietary = fiber_dietary;
        }

        public String getVitamin_a() {
            return vitamin_a;
        }

        public void setVitamin_a(String vitamin_a) {
            this.vitamin_a = vitamin_a;
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

        public String getCarotene() {
            return carotene;
        }

        public void setCarotene(String carotene) {
            this.carotene = carotene;
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

        public String getNiacin() {
            return niacin;
        }

        public void setNiacin(String niacin) {
            this.niacin = niacin;
        }

        public String getCholesterol() {
            return cholesterol;
        }

        public void setCholesterol(String cholesterol) {
            this.cholesterol = cholesterol;
        }

        public String getMagnesium() {
            return magnesium;
        }

        public void setMagnesium(String magnesium) {
            this.magnesium = magnesium;
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

        public String getZinc() {
            return zinc;
        }

        public void setZinc(String zinc) {
            this.zinc = zinc;
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

        public String getKalium() {
            return kalium;
        }

        public void setKalium(String kalium) {
            this.kalium = kalium;
        }

        public String getPhosphor() {
            return phosphor;
        }

        public void setPhosphor(String phosphor) {
            this.phosphor = phosphor;
        }

        public String getNatrium() {
            return natrium;
        }

        public void setNatrium(String natrium) {
            this.natrium = natrium;
        }

        public String getSelenium() {
            return selenium;
        }

        public void setSelenium(String selenium) {
            this.selenium = selenium;
        }
    }

    public static class LightsBean {
        private String calory;
        private String protein;
        private String carbohydrate;
        private String fat;
        private String fiber_dietary;
        private String gi;
        private String gl;

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }

        public String getProtein() {
            return protein;
        }

        public void setProtein(String protein) {
            this.protein = protein;
        }

        public String getCarbohydrate() {
            return carbohydrate;
        }

        public void setCarbohydrate(String carbohydrate) {
            this.carbohydrate = carbohydrate;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
            this.fat = fat;
        }

        public String getFiber_dietary() {
            return fiber_dietary;
        }

        public void setFiber_dietary(String fiber_dietary) {
            this.fiber_dietary = fiber_dietary;
        }

        public String getGi() {
            return gi;
        }

        public void setGi(String gi) {
            this.gi = gi;
        }

        public String getGl() {
            return gl;
        }

        public void setGl(String gl) {
            this.gl = gl;
        }
    }

    public static class UnitsBean {
        private int unit_id;
        private String amount;
        private String unit;
        private String weight;
        private String eat_weight;
        private String calory;

        public int getUnit_id() {
            return unit_id;
        }

        public void setUnit_id(int unit_id) {
            this.unit_id = unit_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getEat_weight() {
            return eat_weight;
        }

        public void setEat_weight(String eat_weight) {
            this.eat_weight = eat_weight;
        }

        public String getCalory() {
            return calory;
        }

        public void setCalory(String calory) {
            this.calory = calory;
        }
    }

    public static class HealthAppraiseBean {
        private int health_mode;
        private int show;
        private int light;
        private String appraise;

        public int getHealth_mode() {
            return health_mode;
        }

        public void setHealth_mode(int health_mode) {
            this.health_mode = health_mode;
        }

        public int getShow() {
            return show;
        }

        public void setShow(int show) {
            this.show = show;
        }

        public int getLight() {
            return light;
        }

        public void setLight(int light) {
            this.light = light;
        }

        public String getAppraise() {
            return appraise;
        }

        public void setAppraise(String appraise) {
            this.appraise = appraise;
        }
    }
}
