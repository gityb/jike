package com.ssm.nowgo.pojo;

import java.util.List;

public class Plane {
    private String trainName;
    private String StartStationName;
    private String EndStationName;
    private String StartTime;
    private String EndTime;
    private List<Seat> seats;

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getStartStationName() {
        return StartStationName;
    }

    public void setStartStationName(String startStationName) {
        StartStationName = startStationName;
    }

    public String getEndStationName() {
        return EndStationName;
    }

    public void setEndStationName(String endStationName) {
        EndStationName = endStationName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }
}
