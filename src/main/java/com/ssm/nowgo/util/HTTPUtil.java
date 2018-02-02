package com.ssm.nowgo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author menglili
 * @date 2015年12月9日
 */
public class HTTPUtil {

    public static void main(String[] args) {

    }
    public static String train(String startName, String endName,String queryDate){
        String url = "http://trains.ctrip.com/TrainBooking/Ajax/SearchListHandler.ashx?Action=getSearchList";
        String param = "value={\"IsBus\":false,\"Filter\":\"0\",\"Catalog\":\"\",\"IsGaoTie\":false,\"IsDongChe\":false,\"CatalogName\":\"\"," +
                "\"DepartureCity\":\"\",\"ArrivalCity\":\"\",\"HubCity\":\"\",\"DepartureCityName\":\""+startName+"\",\"ArrivalCityName\":" +
                "\""+endName+"\",\"DepartureDate\":\""+queryDate+"\",\"DepartureDateReturn\":\"\",\"ArrivalDate\":\"\",\"TrainNumber\":\"\"} ";
        String result = HTTPUtil.httpPost(url, param, "gbk");
        return result;
    }

    public static String httpPost(String urlStr, String params, String charSet) {
        HttpURLConnection httpConn=null;
        try {
            byte[] data=params.getBytes(charSet);
            URL url=new URL(urlStr);
            httpConn=(HttpURLConnection)url.openConnection();
            httpConn.setRequestMethod("POST");
            httpConn.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
            httpConn.setRequestProperty("Content-Length", String.valueOf(data.length));
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//jdk1.4换成这个,连接超时
            // System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //jdk1.4换成这个,读操作超时
            httpConn.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
            httpConn.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
            httpConn.connect();
            OutputStream os1=httpConn.getOutputStream();
            os1.write(data);
            os1.flush();
            os1.close();
            return getResponseResult(httpConn, urlStr, charSet);
        } catch(Exception ex) {
            ex.printStackTrace();
            if(ex instanceof java.net.ConnectException || ex instanceof java.net.SocketTimeoutException) {
                NetUtil.clearDNSCache();
            }
        } finally {
            if(null != httpConn) {
                httpConn.disconnect();
            }
        }
        return null;
    }
    
    


    public static String httpGet(String urlStr, String params, String charSet) {
        HttpURLConnection httpConn=null;
        try {
            if(null != params && params.length() > 0) {
                if(urlStr.indexOf("?") == -1) {
                    urlStr+="?" + params;
                } else {
                    urlStr+="&" + params;
                }
            }
            URL url=new URL(urlStr);
            httpConn=(HttpURLConnection)url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("ContentType", "application/x-www-form-urlencoded");
            httpConn.setDoInput(true);
            httpConn.setDoOutput(true);
            // System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//jdk1.4换成这个,连接超时
            // System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //jdk1.4换成这个,读操作超时
            httpConn.setConnectTimeout(30000);// jdk 1.5换成这个,连接超时
            httpConn.setReadTimeout(30000);// jdk 1.5换成这个,读操作超时
            httpConn.connect();
            return getResponseResult(httpConn, urlStr, charSet);
        } catch(Exception ex) {
            if(ex instanceof java.net.ConnectException || ex instanceof java.net.SocketTimeoutException) {
                NetUtil.clearDNSCache();
            }
        } finally {
            if(null != httpConn) {
                httpConn.disconnect();
            }
        }
        return null;
    }
    

    private static String getResponseResult(HttpURLConnection httpConn, String urlStr, String charSet) throws IOException {
        String res=null;
        // 获得响应状态
        int responseCode=httpConn.getResponseCode();
        if(HttpURLConnection.HTTP_OK == responseCode) {
            byte[] buffer=new byte[1024];
            int len=-1;
            InputStream is=httpConn.getInputStream();
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            while((len=is.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            res=bos.toString(charSet);
            is.close();
            bos.close();
        } else {
        }
        return res;
    }

}
