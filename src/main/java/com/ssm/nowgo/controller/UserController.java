package com.ssm.nowgo.controller;

import com.ssm.nowgo.pojo.User;
import com.ssm.nowgo.pojo.Visit;
import com.ssm.nowgo.service.LoginService;
import com.ssm.nowgo.service.VisitService;
import com.ssm.nowgo.util.JsonUtil;
import com.ssm.nowgo.util.UploadUtile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class UserController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private VisitService visitService;


    @RequestMapping(value = "/userInfo.html",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> update(MultipartFile userImg,User user){

        if (userImg !=null){
             user.setHeadImg("/images/"+UploadUtile.uploadFile(userImg,"/usr/local/tomcat/webapps/images"));
            /*user.setHeadImg("www.kunyun.xyz/images/"+UploadUtile.uploadFile(userImg,"D://main"));*/
        }else {
            User user1 = loginService.getUserById(user.getId());
            user.setHeadImg(user1.getHeadImg());
        }
        try {
            loginService.updateUserInfo(user);
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.getError("修改失败");
        }
        return JsonUtil.getSuccess();
    }



    @RequestMapping("/mine.html")
    @ResponseBody
    public Map<String,Object> mine(@RequestParam Integer id) {
        User user = loginService.getUserById(id);
        Map<String,Object> result = new HashMap<>();
        Map<String,Object> data = new HashMap<>();
        result.put("success",true);
        result.put("result",true);
        data.put("nickname",user.getNickname());
        data.put("fansNum",user.getFansNum());
        data.put("attentionNum",user.getAttentionNum());
        data.put("desc",user.getDesc());
        if (user.getBirth()!=null){
            SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
            data.put("birth",sdf.format(user.getBirth()));
        }else {
            data.put("birth",null);
        }
        data.put("sex",user.getSex());
        data.put("headImg",user.getHeadImg());
        result.put("data",data);
        return result;
    }

    @RequestMapping("/userVisit.html")
    @ResponseBody
    public Map<String,Object> get(Integer id){
        List<Visit> visitList = visitService.getVisitByUserId(id);
        List<Map<String,String>> list= new ArrayList<>();
        for (Visit visit : visitList) {
            Map<String,String> map = new HashMap<>();
            map.put("title",visit.getTitle());
            map.put("article",visit.getContent());
            SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
            map.put("createdDate",sdf.format(visit.getCreatedDate()));
            list.add(map);
        }
        Map<String,Object> map1=new HashMap<>();
        map1.put("list",list);
        return JsonUtil.getMessage(map1);
    }

}
