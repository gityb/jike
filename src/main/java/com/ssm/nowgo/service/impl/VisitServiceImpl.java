package com.ssm.nowgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.dao.VisitDao;
import com.ssm.nowgo.pojo.Visit;
import com.ssm.nowgo.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VisitServiceImpl implements VisitService {
    @Autowired
    private VisitDao visitDao;
    @Override
    public void addVisit(Visit visit) {
        visitDao.addVisit(visit);
    }

    @Override
    public int getVisitCount(String city) {
        return visitDao.getVisitCount(city);
    }

    @Override
    public PageInfo<Visit> getVisitList(String name) {
        PageHelper.startPage(1,6,"id desc");
        List<Visit> visitList= visitDao.getVisitList(name);
        PageInfo<Visit> pageInfo=new PageInfo<>(visitList);
        return pageInfo;
    }

    @Override
    public PageInfo<Visit> getVisit(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,"view_count desc");
        List<Visit> visitList= visitDao.getVisit();
        PageInfo<Visit> pageInfo=new PageInfo<>(visitList);
        return pageInfo;
    }

    @Override
    public PageInfo<Visit> getVisitByPlace(String city) {
        PageHelper.startPage(1,9,"view_count desc");
        List<Visit> visitList= visitDao.getVisitByPlace(city);
        PageInfo<Visit> pageInfo=new PageInfo<>(visitList);
        return pageInfo;
    }

    @Override
    public Visit getVisitById(Integer id) {
        return visitDao.getVisitById(id);
    }

    @Override
    public PageInfo<Visit> getStrategyBySearch(String search) {
        PageHelper.startPage(1,6,"view_count desc");
        List<Visit> visitList = visitDao.getStrategyBySearch(search);
        return new PageInfo<>(visitList);
    }

    @Override
    public List<Visit> getVisitByUserId(Integer userId) {
        return visitDao.getVisitByUserId(userId);
    }
}
