package com.ssm.nowgo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.dao.HotleDao;
import com.ssm.nowgo.pojo.Hotel;
import com.ssm.nowgo.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotleDao hotleDao;

    @Override
    public PageInfo<Hotel> getHotelList(String name, int pageNum) {
        PageHelper.startPage(pageNum,8,"id desc");
        List<Hotel> hotelList=hotleDao.getHotelList(name);
        PageInfo<Hotel> pageInfo=new PageInfo<Hotel>(hotelList);
        return pageInfo;
    }

    @Override
    public PageInfo<Hotel> getGroomList(String name) {
        PageHelper.startPage(1,8);
        List<Hotel> list=hotleDao.getGroomList(name);
        return new PageInfo<>(list);
    }
    @Override
    public List<Hotel> getHotelList(String city) {
        List<Hotel> hotelList=hotleDao.getHotelList(city);
        return hotelList;
    }
}
