package com.example.dllo.foodpie.web;

/**
 * Created by dllo on 16/10/27.
 */
public final class TheValues {

    // 首页食物百科接口
    //逛吃界面--首页接口
    public static String EAT_HOMEPAGE = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";
    //逛吃--首页--下拉刷新--后半段
    public static String EAT_HOMEPAGE_DOWN_AFTER = "&category=1&per=10";
    //逛吃界面--测评接口
    public static String EAT_TEST = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10";
    //逛吃--测试--知识--首页--美食--下拉刷新--前半段
    public static String EAT_DOWN_BEFORE = "http://food.boohee.com/fb/v1/feeds/category_feed?page=";
    //逛吃--测试--下拉刷新--后半段
    public static String EAT_TEST_DOWN_AFTER = "&category=2&per=10";
    //逛吃界面--知识接口
    public static String EAT_KNOWLEDGE = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10";
    //逛吃--知识--下拉刷新--后半段
    public static String EAT_KNOWLEDGE_DOWN_AFTER ="&category=3&per=10";
    //逛吃界面--美食接口
    public static String EAT_BEAUTY = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10";
    //逛吃--美食--下拉刷新--后半段
    public static String EAT_BEAUTY_DOWN_AFTER ="&category=4&per=10";
    //食物百科--详情--营养素排序pop
    public static String FOOD_DESCRIPTION_LINE = "http://food.boohee.com/fb/v1/foods/sort_types";
    //食物百科--详情--下拉刷新--group--前半部分
    public static String FOOD_DESCRIPTION_DOWN_GROUP_BEFORE = "http://food.boohee.com/fb/v1/foods?kind=group&value=";
    //食物百科--详情--下拉刷新--brand--前半部分
    public static String FOOD_DESCRIPTION_DOWN_BRAND_BEFORE = "http://food.boohee.com/fb/v1/foods?kind=brand&value=";
    //食物百科--详情--下拉刷新--restaurant--前半部分
    public static String FOOD_DESCRIPTION_DOWN_RESTAURANT_BEFORE = "http://food.boohee.com/fb/v1/foods?kind=restaurant&value=";
    //食物百科--详情--下拉刷新--后半部分(上边三个通用后边的)
    public static String FOOD_DESCRIPTION_DOWN_AFTER = "&order_by=1&page=";
    //食物百科--首页界面
    public static String FOOD_FOOD = "http://food.boohee.com/fb/v1/categories/list";
    //食物百科--详情界面--右侧pop--根据属性排序后半部分
    public static String FOOD_DESCRIPTION_DOWN_LINE_AFTER = "&order_by=1&page=1&order_asc=0";
    /**
     * 详情界面的内容网址和下拉刷新是一样的
     */
//-------------------------------------------------------------------
    //食物百科--搜索界面--大家都在搜RecyclerView
    public static String FOOD_SEARCH_FIRST_HOT = "http://food.boohee.com/fb/v1/keywords?token=&user_key=&app_version=2.6";
    //食物百科--搜索界面--跳转到第二个界面listView的网络请求
    public static String FOOD_SEARCH_SECOND_LV_BEFORE = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=";
    //食物百科--搜索界面--第二个界面--checkBox点击(选中和不选中的差别就是: 选中的拼接后边有"&health_light=1")
    public static String FOOD_SEARCH_SECOND_CHECK_BEFORE = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q";
    //食物百科--搜索界面--第二个界面--上拉加载前半部分(代码拼接就是拼接pager和后边的q)
    public static String FOOD_SEARCH_SECOND_UP_REFRESH_BEFORE = "http://food.boohee.com/fb/v1/search?page=";
    //食物百科--搜索界面--第二个界面--由低到高前半段 + Q
    public static String FOOD_SEARCH_SECOND_DOWN_TO_UP = "http://food.boohee.com/fb/v1/search?page=1&order_asc=desc&q=";
    //食物百科--搜索界面--第二个界面--由高到低前半段 + Q
    public static String FOOD_SEARCH_SECOND_UP_TO_DOWN = "http://food.boohee.com/fb/v1/search?page=1&order_asc=asc&q=";
    /**
     * POP界面的点击和第一次进入加载的前半段是一样的
     */




}
