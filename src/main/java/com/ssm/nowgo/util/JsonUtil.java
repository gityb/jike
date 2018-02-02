package com.ssm.nowgo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {
    public static Map<String,Object> getSuccessMessage(String message,Object other){
        Map<String,Object> result=new HashMap();
        result.put("status",true);
        result.put("message",message);
        result.put("other",other);
        return result;
    }

    public static  Map<String,Object> getErrorMessage(String message,Object other){
        Map<String,Object> result=new HashMap();
        result.put("status",false);
        result.put("message",message);
        result.put("other",other);
        return  result;
    }

    public static  Map<String,Object> getMessage(Object other){
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("result",true);
        result.put("data",other);
        return  result;
    }
    public static  Map<String,Object> getSuccess( ){
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("result",true);
        result.put("error",null);
        return  result;
    }public static  Map<String,Object> getError(String srt){
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("result",false);
        result.put("error",srt);
        return  result;
    }
    /**
     * 将Java对象转化为JSON字符串
     */
    public static String getJSON(Object obj) throws IOException {
        if (null == obj) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper();
        //转换date类型的时候，时间戳
        mapper.getSerializationConfig().with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String jsonStr = mapper.writeValueAsString(obj);
        return jsonStr;
    }

    /**
     * 将JSON字符串转化为Java对象，集合
     */
    public static <T> T getObj(String json, TypeReference<T> ref)
            throws IOException {
        if (null == json || json.length() == 0) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.getDeserializationConfig().with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return (T) mapper.readValue(json, ref);
    }

    /**
     * 将JSON字符串转化为Java对象，一个对象
     */
    public static Object getObj(String json, Class pojoClass) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, pojoClass);
    }

    /**
     * 本工程中页面用ajax请求成功时，统一使用这个方法封装
     * 页面根据statusCode判断请求是否成功
     */
    public static Map<String, String> getOkStatusMsg(String msg) {
        Map<String, String> res = new HashMap<String, String>();
        res.put("statusCode", "200");
        if (null != msg && msg.length() > 0) {
            res.put("message", msg);
        }
        return res;
    }

    /**
     * 本工程中页面用ajax请求失败时，统一使用这个方法封装
     * 页面根据statusCode判断请求是否成功
     */
    public static Map<String, String> getErrorStatusMsg(String msg) {
        Map<String, String> res = new HashMap<String, String>();
        res.put("statusCode", "300");
        res.put("message", msg);
        return res;
    }

}
