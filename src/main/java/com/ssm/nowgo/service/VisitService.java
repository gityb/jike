package com.ssm.nowgo.service;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.pojo.Strategy;
import com.ssm.nowgo.pojo.Visit;

import java.util.List;

public interface VisitService {
    void addVisit(Visit visit);
    int  getVisitCount(String city);
    PageInfo<Visit> getVisitList(String name);
    PageInfo<Visit> getVisit(Integer pageNum,Integer pageSize);
    PageInfo<Visit> getVisitByPlace(String city);
    Visit getVisitById(Integer id);
    PageInfo<Visit> getStrategyBySearch(String search);
    List<Visit> getVisitByUserId(Integer userId);

}
