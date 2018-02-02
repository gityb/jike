package com.ssm.nowgo.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.pojo.*;
import com.ssm.nowgo.service.*;
import com.ssm.nowgo.util.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//目的地
@Controller
@RequestMapping("/common")
public class PlaceController {
    @Autowired
    private PlaceService placeService;
    @Autowired
    private VisitService visitService;
    @Autowired
    private StrategyService strategyService;
    @Autowired
    private AdvertService advertService;
    @Autowired
    private CityService cityService;

    @RequestMapping("/place.html")
    @ResponseBody
    public Map<String,Object> list(String city, @RequestParam(defaultValue = "1")Integer pageNum) throws Exception{
        int visitCount = visitService.getVisitCount(city);
        PageInfo<Visit> pageInfo;
        PageInfo<Strategy> pageInfo1;
        PageInfo<Place> pageInfo2=placeService.getPlaceByCount1();
        if (visitCount!=0){
            pageInfo = visitService.getVisitByPlace(city);
            pageInfo1=strategyService.getStrategyByPlace(pageNum,city)  ;
        }else {
            pageInfo= visitService.getVisit(1,9);
            pageInfo1=strategyService.getStrategy(1,9)  ;
        }
      PageInfo<Advert> adds = advertService.getAdvertList(1,1);
      String[] temp = WeatherUtil.weather(city);
      Place place =placeService.getPlace(city);
       List<String> addlist = new ArrayList<>();
        for (Advert advert : adds.getList()) {
            addlist.add(advert.getImgUrl());
        }
        Map<String,Object> data=new HashMap<>();
        Map<String,Object> header = new HashMap<>();
        List<String> addList = new ArrayList<>();
        if(place!=null){
            addList.add(place.getImgUrl());
            addList.add(place.getImgUrl());
            addList.add(place.getImgUrl());
        }else {
            addList.add("http://img1.imgtn.bdimg.com/it/u=918738661,1036578696&fm=27&gp=0.jpg");
            addList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3907021226,2855429702&fm=27&gp=0.jpg");
            addList.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=52263362,941824610&fm=27&gp=0.jpg");
        }

        header.put("temperature",temp[0]);
        header.put("weather",temp[1]);
        header.put("viewImg",addList);
        header.put("searchtxt","年终大促爆款清单");
        data.put("headerInfo",header);
        data.put("travels",pageInfo.getList());
        data.put("adds",adds.getList());
        data.put("strategy",pageInfo1.getList());
        data.put("views",pageInfo2.getList());
        Map<String,Object> result=new HashMap<>();
        result.put("data",data);
        return result;
    }

    @RequestMapping("/city_list.html")
    @ResponseBody
    public Map<String,Object> getList(){
        Map<String,Object> result=new HashMap<>();
        Map<String,Object> data=new HashMap<>();
        result.put("data",data);
        result.put("ret",true);
        Map<String,Object> list = new HashMap<>();
        list.put("A",cityService.getCity(1));
        list.put("B",cityService.getCity(2));
        list.put("C",cityService.getCity(3));
        list.put("D",cityService.getCity(4));
        list.put("E",cityService.getCity(5));
        list.put("F",cityService.getCity(6));
        list.put("G",cityService.getCity(7));
        list.put("H",cityService.getCity(8));
        list.put("J",cityService.getCity(10));
        list.put("K",cityService.getCity(11));
        list.put("L",cityService.getCity(12));
        list.put("M",cityService.getCity(13));
        list.put("N",cityService.getCity(14));
        list.put("P",cityService.getCity(16));
        list.put("Q",cityService.getCity(17));
        list.put("R",cityService.getCity(18));
        list.put("S",cityService.getCity(19));
        list.put("T",cityService.getCity(20));
        list.put("W",cityService.getCity(23));
        list.put("X",cityService.getCity(24));
        list.put("Y",cityService.getCity(25));
        list.put("Z",cityService.getCity(26));
        data.put("list",list);
        return result;
    }


}
