package com.ssm.nowgo.dao;

import com.ssm.nowgo.pojo.Hotel;

import java.util.List;

public interface HotleDao {
    List<Hotel> getHotelList(String name);
    List<Hotel> getGroomList(String name);
}
