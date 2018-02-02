package com.ssm.nowgo.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.pojo.Visit;
import com.ssm.nowgo.service.VisitService;
import com.ssm.nowgo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/visit")
public class VisitController {
    @Autowired
    private VisitService visitService;

    @RequestMapping("/list.html")
    @ResponseBody
    public Map<String,Object> list(){
        PageInfo<Visit> visit = visitService.getVisit(1,4);
        PageInfo<Visit> visit2 = visitService.getVisit(2,36 );
        Map<String,Object> data = new HashMap<>();
        data.put("banner",visit.getList());
        data.put("list",visit2.getList());
        return JsonUtil.getMessage(data);
    }
    @RequestMapping("/get.html")
    @ResponseBody
    public Map<String,Object> getVisitById(Integer id){
        Visit visit = visitService.getVisitById(id);
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        result.put("result",true);
        Map<String,Object> data = new HashMap<>();
        result.put("data",data);
        data.put("visit",visit);
        return  result;
    }

    @RequestMapping("/add.html")
    @ResponseBody
    public Map<String,Object> add(){




        return JsonUtil.getSuccess();
    }

}
