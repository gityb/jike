package com.ssm.nowgo.service;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.pojo.Strategy;

import java.util.List;


public interface StrategyService {
   PageInfo<Strategy> getStrategyList(String name);
   PageInfo<Strategy> getStrategy(Integer pageNum,Integer pageSize);
   PageInfo<Strategy> getStrategyByPlace(Integer pageNum,String city);
   Strategy getStrategyById(Integer id);
   PageInfo<Strategy> getStrategyBySearch(String search);
}
