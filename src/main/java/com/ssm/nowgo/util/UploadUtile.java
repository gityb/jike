package com.ssm.nowgo.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class UploadUtile {
    public static  String upload(MultipartFile file, String srt, String str1){
        String oldFileName= file.getOriginalFilename();
        String extName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName=System.currentTimeMillis()+extName;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(srt,newFileName));
            return "{\"error\":0,\"url\":\""+str1+newFileName+"\"}";
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\":1}";
        }
    }
    public static  String uploadFile(MultipartFile file, String srt){
        String oldFileName= file.getOriginalFilename();
        String extName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName=System.currentTimeMillis()+extName;
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(srt,newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newFileName;
    }

}
