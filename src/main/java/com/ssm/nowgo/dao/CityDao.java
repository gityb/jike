package com.ssm.nowgo.dao;

import com.ssm.nowgo.pojo.City;

import java.util.List;

public interface CityDao {
    List<City> getCity(Integer id);
}
