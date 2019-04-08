package com.luxsuen.requestjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luxsuen.requestjson.exception.ExceptionCode;
import com.luxsuen.requestjson.exception.LuxsException;

public class JsonUtil {

    /**
     * @Author Lux Sun
     * @Description: 获取普通类型 & 集合
     * @Param: [jsonObj, returnType, keyArray]
     * @Return: java.lang.Object
     */
    public static Object getJsonValueByKeyArray(Object jsonObj, Class<?> returnType, String... keyArray)
    {
        Object rsObj = null;
        try {
            rsObj = returnType.cast(returnJsonObject(jsonObj, keyArray));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rsObj;
    }

    /**
     * @Author Lux Sun
     * @Description: 获取单个实体类
     * @Param: [jsonObj, returnType, keyArray]
     * @Return: java.lang.Object
     */
    public static Object getJsonEntityByKeyArray(Object jsonObj, Class<?> returnType, String... keyArray)
    {
        Object rsObj = null;
        try {
            rsObj = JSON.parseObject(((JSONObject)returnJsonObject(jsonObj, keyArray)).toString(), returnType);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rsObj;
    }

    /**
     * @Author Lux Sun
     * @Description: 获取实体类数组
     * @Param: [jsonObj, returnType, keyArray]
     * @Return: java.lang.Object
     */
    public static Object getJsonEntityListByKeyArray(Object jsonObj, Class<?> returnType, String... keyArray)
    {
        Object rsObj = null;
        try {
             rsObj = JSON.parseArray(((JSONArray)returnJsonObject(jsonObj, keyArray)).toString(), returnType);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return rsObj;
    }

    private static Object returnJsonObject(Object jsonObj, String... keyArray)
    {
        try {
            if(jsonObj == null)
            {
                throw new LuxsException(ExceptionCode.SYSTEEM_ERROR.getCode(), ExceptionCode.SYSTEEM_ERROR.getDesc() + ": 传入 JSONObject 为空对象");
            }

            int len = keyArray.length;
            Class<?> clazz = jsonObj.getClass();
            String key = null;
            for(int i=0; i<len; i++)
            {
                key = keyArray[i];

                if(clazz.isAssignableFrom(JSONArray.class))
                {
                    if(i + 1 <= len)
                    {
                        jsonObj = ((JSONArray) jsonObj).get(Integer.valueOf(key));
                    }
                }
                else if(clazz.isAssignableFrom(JSONObject.class))
                {
                    jsonObj = ((JSONObject)jsonObj).get(key);
                }

                if(jsonObj == null)
                {
                    throw new LuxsException(ExceptionCode.SYSTEEM_ERROR.getCode(), ExceptionCode.SYSTEEM_ERROR.getDesc() + ": 找不到 [key = \"" + key + "\"] 所对应的值");
                }

                clazz = jsonObj.getClass();
            }
        }
        catch (LuxsException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObj;
    }
}