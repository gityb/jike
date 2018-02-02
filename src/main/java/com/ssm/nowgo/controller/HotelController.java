package com.ssm.nowgo.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.pojo.Hotel;
import com.ssm.nowgo.pojo.Strategy;
import com.ssm.nowgo.service.HotelService;
import com.ssm.nowgo.service.StrategyService;
import com.ssm.nowgo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class HotelController {
    @Autowired
    private  HotelService hotelService;
    @Autowired
    private StrategyService strategyService;

    @RequestMapping("/hotel.html")
    @ResponseBody
    public Map<String,Object> list(String city,String name,@RequestParam(defaultValue = "1") Integer pageNum){
        Map<String,Object> data=new HashMap();
        Map<String,Object> result=new HashMap();
        PageInfo<Hotel> list =hotelService.getGroomList("特色酒店");
        PageInfo<Hotel> pageInfo=hotelService.getHotelList(city,1);
        PageInfo<Strategy> strategyPageInfo=strategyService.getStrategyByPlace(pageNum,city);
        data.put("recommend",list.getList());
        data.put("strategy",strategyPageInfo.getList());
        result.put("data",data);
        return  result;
     }

     @RequestMapping("/changeRecommend.html")
     @ResponseBody
     public Map<String,Object> change(String name){
         Map<String,Object> data=new HashMap();
         Map<String,Object> result=new HashMap();
         PageInfo<Hotel> pageInfo=hotelService.getHotelList(name,1);
         data.put("recommend",pageInfo.getList());
         result.put("data",data);
         return  result;
     }

    @RequestMapping("/groom.html")
    @ResponseBody
    public Map<String,Object> groom(String groom){
        PageInfo<Hotel> list =hotelService.getGroomList(groom);
        return JsonUtil.getMessage(list.getList());
    }

    @RequestMapping("/page.html")
    @ResponseBody
    public Map<String,Object> page(@RequestParam Integer pageNum,@RequestParam String city){
        PageInfo<Strategy> pageInfo1=strategyService.getStrategyByPlace(pageNum,city);
        if (pageNum>pageInfo1.getPages()){
            Map<String,Object> result=new HashMap<>();
            result.put("ret",true);
            result.put("success",false);
            return result;
        }
        Map<String,Object> result=new HashMap<>();
        Map<String,Object> data=new HashMap<>();
        result.put("data",data);
        result.put("ret",true);
        data.put("strategy",pageInfo1.getList());
        return result;
    }

}
