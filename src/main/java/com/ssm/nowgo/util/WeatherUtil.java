package com.ssm.nowgo.util;


import com.ssm.nowgo.aliyun.api.gateway.demo.Client;
import com.ssm.nowgo.aliyun.api.gateway.demo.Request;
import com.ssm.nowgo.aliyun.api.gateway.demo.Response;
import com.ssm.nowgo.aliyun.api.gateway.demo.constant.Constants;
import com.ssm.nowgo.aliyun.api.gateway.demo.constant.HttpHeader;
import com.ssm.nowgo.aliyun.api.gateway.demo.constant.HttpSchema;
import com.ssm.nowgo.aliyun.api.gateway.demo.enums.Method;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WeatherUtil {
    //APP KEY
    private final static String APP_KEY = "24777777";
    // APP密钥
    private final static String APP_SECRET = "704071e384bc64d1dd224174db7fcb50";
    //API域名
    private final static String HOST = "jisutqybmf.market.alicloudapi.com";
    //自定义参与签名Header前缀（可选,默认只有"X-Ca-"开头的参与到Header签名）
    private final static List<String> CUSTOM_HEADERS_TO_SIGN_PREFIX = new ArrayList<String>();

    /**
     * HTTP GET
     *
     * @throws Exception
     */
    public static String get(String city) throws Exception {
        //请求path
        String path = "/weather/query";

        Map<String, String> headers = new HashMap<String, String>();
        //（必填）根据期望的Response内容类型设置
        headers.put(HttpHeader.HTTP_HEADER_ACCEPT, "application/");
        // headers.put("a-header1", "header1Value");
        // headers.put("b-header2", "header2Value");

        CUSTOM_HEADERS_TO_SIGN_PREFIX.clear();
        // CUSTOM_HEADERS_TO_SIGN_PREFIX.add("a-header1");
        // CUSTOM_HEADERS_TO_SIGN_PREFIX.add("a-header2");

        Request request = new Request(Method.GET, HttpSchema.HTTP + HOST, path, APP_KEY, APP_SECRET, Constants.DEFAULT_TIMEOUT);
        request.setHeaders(headers);
        request.setSignHeaderPrefixList(CUSTOM_HEADERS_TO_SIGN_PREFIX);

        //请求的query
        Map<String, String> querys = new HashMap<>();
        querys.put("city", city);
        // querys.put("b-query2", "query2Value");
        request.setQuerys(querys);

        //调用服务端
        Response response = Client.execute(request);
        return response.getBody();
    }
    public static String[] weather(String city) throws Exception{
        String result = get(city);
        System.out.println(result);
        JSONObject object = JSONObject.fromObject(result);
        Map<String,Object> res= (Map<String, Object>) JSONObject.toBean(object,Map.class);
        Object result1 = res.get("result");
        String str =result1.toString();

        String[] str1 = str.split("weather=");
        String[] srt2 = str1[1].split("}");

        String[] split = str.split("temp=");
        String[] split1 = split[1].split(",");
        String[] weather = new String[2];
        weather[0]=split1[0];
        weather[1]=srt2[0];
        return weather;
    }
}
