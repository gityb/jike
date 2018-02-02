package com.ssm.nowgo.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.pojo.Strategy;
import com.ssm.nowgo.service.StrategyService;
import com.ssm.nowgo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
//首页攻略接口
@Controller
@RequestMapping("/strategy")
public class StrategyController {
    @Autowired
    private StrategyService strategyService;

    @RequestMapping("/list.html")
    @ResponseBody
    public Map<String,Object> list(){
        Map<String,Object> data= new HashMap<>();
        PageInfo<Strategy> banner = strategyService.getStrategy(1, 4);
        PageInfo<Strategy> strategy = strategyService.getStrategy(2, 36);
        data.put("banner",banner.getList());
        data.put("list",strategy.getList());
        return JsonUtil.getMessage(data);
    }
    @RequestMapping("/get.html")
    @ResponseBody
    public Map<String,Object> get(Integer id){
        Strategy strategy = strategyService.getStrategyById(id);
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("result",true);
        Map<String,Object> data = new HashMap<>();
        result.put("data",data);
        data.put("strategy",strategy);
        return  result;
    }

}
