package com.ssm.nowgo.controller;

import com.ssm.nowgo.pojo.Score;
import com.ssm.nowgo.service.ScoreService;
import com.ssm.nowgo.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @RequestMapping(value = "/add.html")
    @ResponseBody
    public Map<String,Object> add(Score score){
        try {
            scoreService.addScore(score.getPoint());
            return JsonUtil.getSuccess();
        }catch (Exception e){
            return JsonUtil.getError("提交失败");
        }
    }
}
