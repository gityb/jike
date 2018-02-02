package com.ssm.nowgo.controller;

import com.ssm.nowgo.pojo.Hotel;
import com.ssm.nowgo.service.HotelService;
import com.ssm.nowgo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hotel")
public class HotelListController {
    @Autowired
    private HotelService hotelService;

    @RequestMapping("/list.html")
    @ResponseBody
    public Map<String,Object> list(String city){
        List<Hotel> hotelList = hotelService.getHotelList(city);
        return JsonUtil.getMessage(hotelList);
    }

}
