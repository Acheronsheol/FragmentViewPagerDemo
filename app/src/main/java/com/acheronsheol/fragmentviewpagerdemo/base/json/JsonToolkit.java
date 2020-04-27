package com.acheronsheol.fragmentviewpagerdemo.base.json;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
        return new Gson().fromJson(jsonStr, beanClass);
    }

    /*
    * json字符串转JsonObject
    * */
    public static JsonObject jsonStringToJsonObject(String jsonStr, Class beanClass) {
        return new JsonParser().parse(jsonStr).getAsJsonObject();
    }

}
