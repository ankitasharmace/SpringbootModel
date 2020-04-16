package com.example.demo;

import io.searchbox.annotations.JestId;
import io.searchbox.core.Index;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.io.Serializable;
public class HotelData implements Serializable {
    @JestId
    private String hotelid;

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    private String latitude;
    private String longitude;
    private int sprice;
    private int fprice;
    private int rating;
    private int rcount;
    private String displayCategory;
    private int bookingFlag;
    private int detailsFlag;
    private int impressionFlag;
    private int cityId;
    public HotelData(){

    }
    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }



    public int getBookingFlag() {
        return bookingFlag;
    }

    public void setBookingFlag(int bookingFlag) {
        this.bookingFlag = bookingFlag;
    }

    public int getDetailsFlag() {
        return detailsFlag;
    }

    public void setDetailsFlag(int detailsFlag) {
        this.detailsFlag = detailsFlag;
    }

    public int getImpressionFlag() {
        return impressionFlag;
    }

    public void setImpressionFlag(int impressionFlag) {
        this.impressionFlag = impressionFlag;
    }






    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public int getSprice() {
        return sprice;
    }

    public void setSprice(int sprice) {
        this.sprice = sprice;
    }

    public int getFprice() {
        return fprice;
    }

    public void setFprice(int fprice) {
        this.fprice = fprice;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public String getDisplayCategory() {
        return displayCategory;
    }

    public void setDisplayCategory(String displayCategory) {
        this.displayCategory = displayCategory;
    }



}
