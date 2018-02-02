package com.ssm.nowgo.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ssm.nowgo.pojo.Place;
import com.ssm.nowgo.pojo.Plane;
import com.ssm.nowgo.pojo.Query;
import com.ssm.nowgo.pojo.Seat;
import com.ssm.nowgo.util.HTTPUtil;
import com.ssm.nowgo.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common")
public class TrainController {

    @RequestMapping("/tickets.html")
    @ResponseBody
    public Map<String,Object> trainList(Query query) throws Exception {
        System.out.println(query.getQueryDate());
        String result = HTTPUtil.train(query.getStartName(),query.getEndName(),query.getQueryDate());
        if (result == null || result.length() == 0) {
            return JsonUtil.getError("没有车票信息");
        }
        //System.out.println(result);
        Map<String, Object> ticket = JsonUtil.getObj(result, new TypeReference<Map<String, Object>>() {
        });


        Object trainItemsList = ticket.get("TrainItemsList");

        List<Map<String, Object>> trainList = (List<Map<String, Object>>) trainItemsList;
        if (trainList ==null || trainList.size()==0){
            return JsonUtil.getError("没有车票信息");
        }
        List<Plane> planeList = new ArrayList<>();
        for (Map<String, Object> stringObjectMap : trainList) {
            Plane plane = new Plane();
            plane.setTrainName((String) stringObjectMap.get("TrainName"));
            plane.setStartStationName((String) stringObjectMap.get("StartStationName"));
            plane.setEndStationName((String) stringObjectMap.get("EndStationName"));
            plane.setStartTime((String) stringObjectMap.get("StratTime"));
            plane.setEndTime((String) stringObjectMap.get("EndTime"));
            List<Seat> seats = new ArrayList<>();
            List<Map<String, Object>> seatList = (List<Map<String, Object>>) stringObjectMap.get("SeatBookingItem");
            for (Map<String, Object> objectMap : seatList) {
                Seat seat = new Seat();
                seat.setName((String) objectMap.get("SeatName"));
                seat.setPrice((String) objectMap.get("Price"));
                seat.setInventory((Integer) objectMap.get("Inventory"));
                seats.add(seat);
            }
            plane.setSeats(seats);
            planeList.add(plane);
        }
        return JsonUtil.getMessage(planeList);
    }
}
