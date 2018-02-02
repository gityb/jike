package com.ssm.nowgo.dao;

import com.ssm.nowgo.pojo.Place;

import java.util.List;

public interface PlaceDao {
    List<Place> getPlaceByCount();
    List<Place> getPlaceByCount1();
    Place getPlace(String city);
}
