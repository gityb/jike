package com.ssm.nowgo.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.dao.TypeDao;
import com.ssm.nowgo.pojo.*;
import com.ssm.nowgo.service.AdvertService;
import com.ssm.nowgo.service.IconsService;
import com.ssm.nowgo.service.LoginService;
import com.ssm.nowgo.service.VisitService;
import com.ssm.nowgo.util.ImgUtil;
import com.ssm.nowgo.util.JsonUtil;
import com.ssm.nowgo.util.UploadUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//首页
@Controller
@RequestMapping("/common")
public class IndexController {
    @Autowired
    private VisitService visitService;
    @Autowired
    private AdvertService advertService;
    @Autowired
    private LoginService loginService;
    @RequestMapping("/index.html")
    @ResponseBody
    public Map<String, Object> index(HttpServletResponse response) throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put("ret", true);
        Map<String, Object> data = new HashMap<>();
        PageInfo<Advert> add_top = advertService.getAdvertList(1, 3);//头-广告
        PageInfo<Visit> selection = visitService.getVisitList("精选");
        PageInfo<Visit> baby_trip = visitService.getVisitList("带娃旅行");
        PageInfo<Visit> couple_trip = visitService.getVisitList("情侣旅行");
        PageInfo<Visit> movie_trip = visitService.getVisitList("影视地游");
        data.put("banner", add_top.getList());
        data.put("selection", selection.getList());
        data.put("babyTrip", baby_trip.getList());
        data.put("coupletrip", couple_trip.getList());
        data.put("movietrip", movie_trip.getList());
        result.put("data", data);
        return result;
    }

    @RequestMapping("/publish.html")
    @ResponseBody
    public Map<String,Object> publish(MultipartFile[] file,Visit visit,String text){
        System.out.println(visit.getTitle());
        System.out.println(text);
        System.out.println(visit.getUserId());
        String srt = "<p>";
        srt+=text+"</p>";
        String upload ="";
        for (MultipartFile file1 : file) {
            upload = UploadUtile.uploadFile(file1, "/usr/local/tomcat/webapps/images");
           /* upload = UploadUtile.uploadFile(file1,"D:/main");*/
            String str ="/images/"+ upload;
            srt=srt+ImgUtil.img(str);
        }
        System.out.println(srt);
        visit.setContent(srt);
        User user = loginService.getUserById(visit.getUserId());
        System.out.println(user);
        visit.setUserImg(user.getHeadImg());
        visit.setNickname(user.getNickname());
        visit.setPlace("北京");
        visit.setImgUrl("/images/"+ upload);
        visitService.addVisit(visit);
        return JsonUtil.getSuccess();
    }



}
