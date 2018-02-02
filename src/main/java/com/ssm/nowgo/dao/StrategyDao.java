package com.ssm.nowgo.dao;

import com.ssm.nowgo.pojo.Strategy;

import java.util.List;

public interface StrategyDao {
  List<Strategy> getStrategyList(String name);
  List<Strategy> getStrategy();
  List<Strategy> getStrategyByPlace(String city);
  Strategy getStrategyById(Integer id);
  List<Strategy> getStrategyBySearch(String search);
}
