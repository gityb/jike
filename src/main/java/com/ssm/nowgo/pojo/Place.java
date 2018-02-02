package com.ssm.nowgo.pojo;

public class Place {
    private Integer id;
    private  String city;
    private Integer visiterNum ;
   /* private String placeImg;*/
   private String imgUrl;


    public Integer getVisiterNum() {
        return visiterNum;
    }

    public void setVisiterNum(Integer visiterNum) {
        this.visiterNum = visiterNum;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

   /* public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
*/
   /* public String getPlaceImg() {
        return placeImg;
    }

    public void setPlaceImg(String placeImg) {
        this.placeImg = placeImg;*/
 }
