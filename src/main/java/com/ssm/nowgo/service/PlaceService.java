package com.ssm.nowgo.service;

import com.github.pagehelper.PageInfo;
import com.ssm.nowgo.pojo.Place;


public interface PlaceService {
    PageInfo<Place> getPlaceByCount();
    PageInfo<Place> getPlaceByCount1();
    Place getPlace(String city);
}
