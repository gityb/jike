package com.ssm.nowgo.service;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.pojo.Hotel;

import java.util.List;

public interface HotelService {
    PageInfo<Hotel> getHotelList(String name,int pageNum);
    PageInfo<Hotel> getGroomList(String name);
    public List<Hotel> getHotelList(String city);

}
