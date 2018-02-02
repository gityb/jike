package com.ssm.nowgo.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Query {
    private String startName;//发车站
    private String endName;//到达站
    private String queryDate;//查询时间

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }
}
