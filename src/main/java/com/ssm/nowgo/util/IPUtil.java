package com.ssm.nowgo.util;

import com.ssm.nowgo.util.HTTPUtil;
import com.ssm.nowgo.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class IPUtil {
    public static void main(String[] args) throws Exception {
        //根据用户的ip，查询出他的省和市，然后插入到login_log里

    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str) || "".equals(str.trim());
    }

    //获取用户ip地址
    public static String getUserIpAddr(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("X-Real-IP");
        if (isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (isEmpty(ip) || unknown.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (null != ip && ip.length() != 0) {
            String tmpIps[] = ip.split(",");
            for (String tmpIp : tmpIps) {
                if (null == tmpIp) {
                    continue;
                }
                tmpIp = tmpIp.trim();
                // 过滤本机IP和内网IP，内网IP地址:10.x.x.x;192.168.x.x;172.16.x.x至172.31.x.x。由于172.16.x.x至172.31.x.x比较少见故不做过滤。
                if (tmpIp.length() != 0 && !unknown.equalsIgnoreCase(tmpIp) && tmpIp.indexOf("127.0.0.1") == -1
                        && !tmpIp.startsWith("192.168.") && !tmpIp.startsWith("10.")) {
                    return tmpIp;
                }
            }
        }
        return request.getRemoteAddr();
    }
}
