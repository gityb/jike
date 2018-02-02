package com.ssm.nowgo.dao;

import com.ssm.nowgo.pojo.Visit;

import java.util.List;

public interface VisitDao {
    void addVisit(Visit visit);
    int  getVisitCount(String city);
    List<Visit> getVisitList(String name);
    List<Visit> getVisit();
    List<Visit> getVisitByPlace(String city);
    Visit getVisitById(Integer id);
    List<Visit> getStrategyBySearch(String search);
    List<Visit> getVisitByUserId(Integer userId);
}
