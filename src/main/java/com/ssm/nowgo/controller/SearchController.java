package com.ssm.nowgo.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.pojo.Strategy;
import com.ssm.nowgo.pojo.Visit;
import com.ssm.nowgo.service.StrategyService;
import com.ssm.nowgo.service.VisitService;
import com.ssm.nowgo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private VisitService visitService;
    @Autowired
    private StrategyService strategyService;

    @RequestMapping("/get.html")
    @ResponseBody
    public Map<String,Object> get(String search){
        PageInfo<Strategy> strategyList = strategyService.getStrategyBySearch(search);
        PageInfo<Visit> visitList = visitService.getStrategyBySearch(search);

        List list = new ArrayList();
        for (Visit visit : visitList.getList()) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",visit.getId());
            map.put("title",visit.getTitle());
            map.put("type","游记");
            list.add(map);
        }
        for (Strategy strategy : strategyList.getList()) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",strategy.getId());
            map.put("title",strategy.getTitle());
            map.put("type","攻略");
            list.add(map);
        }
        Map<String,Object> data = new HashMap<>();
        data.put("list",list);
        return JsonUtil.getMessage(data);
    }
}
