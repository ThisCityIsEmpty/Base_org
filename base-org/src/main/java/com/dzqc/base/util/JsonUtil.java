package com.dzqc.base.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.List;


public class JsonUtil {
    private final static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static Gson getInstance(){
        return gson;
    }

    /***
     * List 转为 JSON
     * @param list
     * @return
     */
    public static <T> String list2Json(List<T> list) {
        if(null != list && list.size() > 0){
            JSONArray jsonArray = JSONArray.fromObject(list);
            return jsonArray.toString();
        }
        return "";
    }


    /***
     * JSON 转换为 List
     * @param jsonStr
     *         [{"age":12,"createTime":null,"id":"","name":"wxw","registerTime":null,"sex":1},{...}]
     * @param objectClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> json2List(String jsonStr, Class<T> objectClass){
        if (StringUtils.isNotBlank(jsonStr)) {
            JSONArray jsonArray = JSONArray.fromObject(jsonStr);
            List<T> list = (List<T>) JSONArray.toCollection(jsonArray, objectClass);
            return list;
        }
        return null;
    }


    /***
     * Object 转为  JSON
     * @param object
     * @return
     */
    public static String object2Json(Object object) {
        if(null != object){
            JSONArray jsonArray = JSONArray.fromObject(object);
            return jsonArray.toString();
        }
        return "";
    }

    /***
     *
     * JSON 转 Object
     *
     * @param jsonStr
     *         [{"age":12,"createTime":null,"id":"","name":"wxw","registerTime":null,"sex":1}]
     * @param objectClass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T json2Ojbect(String jsonStr,  Class<T> objectClass){
        if(null != jsonStr){
            String leftStr = jsonStr.substring(0,2);
            String rightStr = jsonStr.substring(jsonStr.length()-2,jsonStr.length());
            if(leftStr.equals("[{")){
                jsonStr = jsonStr.substring(1,jsonStr.length());
            }
            if(rightStr.equals("}]")){
                jsonStr = jsonStr.substring(0,jsonStr.length()-1);
            }
            JSONObject jsonStu = JSONObject.fromObject(jsonStr);
            return (T) JSONObject.toBean(jsonStu,objectClass);
        }
        return null;
    }

    /***
     * JsonArray 转为 JSON
     * @param jsonArray
     */
    public static String jsonArrayToJSONString(JSONArray jsonArray) {
        if(null != jsonArray){
            return jsonArray.toString();
        }
        return "";
    }

    /***
     * JsonObject 转为  JSON
     * @param jsonObject
     */
    public static String jsonObjectToJSONString(JSONObject jsonObject) {
        if(null != jsonObject){
            return jsonObject.toString();
        }
        return "";
    }

    /***
     * 将Object转换为JsonObject
     * @param object
     */
    public static JSONObject object2JsonObject(Object object) {
        if(null != object){
            return JSONObject.fromObject(object);
        }
        return null;
    }

}