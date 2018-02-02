package com.ssm.nowgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.dao.PlaceDao;
import com.ssm.nowgo.pojo.Place;
import com.ssm.nowgo.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    @Autowired
    private PlaceDao placeDao;
    @Override
    public PageInfo<Place> getPlaceByCount() {
        PageHelper.startPage(1,8,"count desc");
        List<Place> placeList=placeDao.getPlaceByCount();
        PageInfo<Place> pageInfo=new PageInfo<>(placeList);
        return pageInfo;
    }

    @Override
    public PageInfo<Place> getPlaceByCount1() {
        PageHelper.startPage(1,12,"count desc");
        List<Place> placeList=placeDao.getPlaceByCount1();
        PageInfo<Place> pageInfo=new PageInfo<>(placeList);
        return pageInfo;
    }

    @Override
    public Place getPlace(String city) {
        return placeDao.getPlace(city);
    }
}
