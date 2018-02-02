package com.ssm.nowgo.service.impl;

import com.ssm.nowgo.dao.CityDao;
import com.ssm.nowgo.pojo.City;
import com.ssm.nowgo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityDao cityDao;

    @Override
    public List<City> getCity(Integer id) {
        return cityDao.getCity(id);
    }
}
