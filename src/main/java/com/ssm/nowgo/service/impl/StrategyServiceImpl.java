package com.ssm.nowgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.dao.StrategyDao;
import com.ssm.nowgo.pojo.Strategy;
import com.ssm.nowgo.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StrategyServiceImpl implements StrategyService {
    @Autowired
    private StrategyDao strategyDao;
    @Override
    public PageInfo<Strategy> getStrategyList(String name) {
        PageHelper.startPage(1,9,"id desc");
        List<Strategy> strategyList=strategyDao.getStrategyList(name);
        PageInfo<Strategy> pageInfo=new PageInfo<>(strategyList);
        return pageInfo;
    }

    @Override
    public PageInfo<Strategy> getStrategy(Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize,"view_count desc");
        List<Strategy> strategyList= strategyDao.getStrategy();
        PageInfo<Strategy> pageInfo=new PageInfo<>(strategyList);
        return pageInfo;
    }

    @Override
    public PageInfo<Strategy> getStrategyByPlace(Integer pageNum,String city) {
        PageHelper.startPage(pageNum,9,"view_count desc");
        List<Strategy> strategyList= strategyDao.getStrategyByPlace(city);
        PageInfo<Strategy> pageInfo=new PageInfo<>(strategyList);
        return pageInfo;
    }

    @Override
    public Strategy getStrategyById(Integer id) {
        return strategyDao.getStrategyById(id);
    }

    @Override
    public PageInfo<Strategy> getStrategyBySearch(String search) {
        PageHelper.startPage(1,5,"view_count desc");
        List<Strategy> strategyList = strategyDao.getStrategyBySearch(search);
        return new PageInfo<>(strategyList);
    }
}
