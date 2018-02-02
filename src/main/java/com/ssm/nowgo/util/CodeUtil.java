package com.ssm.nowgo.util;

public class CodeUtil {
    public static String getCode(){
        String code="";
        int num=(int)(Math.random()*8999+1000);
        code=code+num;
        return code;
    }
}
