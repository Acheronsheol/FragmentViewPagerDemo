package com.acheronsheol.fragmentviewpagerdemo.base.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JsonToolkit {

    /*
    * 实体转Json字符串
    * */
    public static String objectToJsonString(Object bean) {
        return new Gson().toJson(bean);
    }

    /*
    * Json转实体
    *  使用前强制转换 bean bean1 = (bean)FormatUtil.JSONToObject(json, bean.class);
    * */
    public static Object jsonToObject(String jsonStr,Class beanClass) {
        try {
            return new Gson().fromJson(jsonStr, beanClass);
        } catch (Exception e){
            return new Object();
        }
    }

    /*
    * json字符串转JsonObject
    * */
    public static JsonObject jsonStringToJsonObject(String jsonStr) {
        try {
            return new Gson().fromJson(jsonStr, JsonObject.class);
        } catch (Exception e){
            return new JsonObject();
        }
    }

    /*
     * 判断是否为json字符串
     * */
    public static boolean isJsonFormat(String jsonStr) {
        try {
            new Gson().fromJson(jsonStr, Object.class);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

}
