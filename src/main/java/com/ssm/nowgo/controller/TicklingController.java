package com.ssm.nowgo.controller;

import com.ssm.nowgo.pojo.Tickling;
import com.ssm.nowgo.service.TicklingService;
import com.ssm.nowgo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/tickling")
public class TicklingController {
    @Autowired
    private TicklingService ticklingService;

    @RequestMapping(value = "/add.html")
    @ResponseBody
    public Map<String,Object> add(Tickling tickling){
        /*JSONObject tc=JSONObject.fromObject(tickling);
        Tickling tickling1=(Tickling)JSONObject.toBean(tc,Tickling.class);*/
       /* System.out.println(tickling1.getPhone());*/
        try {
            ticklingService.addTickling(tickling);
            return JsonUtil.getSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return JsonUtil.getError("提交失败");
        }
    }
}
