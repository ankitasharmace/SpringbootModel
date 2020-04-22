package com.example.demo.Entities;

import org.springframework.context.annotation.Bean;

import javax.persistence.Entity;

@Entity
public class hotelFeature {
    private String hotelid;
    private float avg_hotel_br;
    private float avg_hotel_ctr;
    private float avg_hotel_btod;
    private float discount_per;
    private int rating;
    private int ratingCount;
    private int Fprice;
    private int Sprice;
    private int bookingFlag;
    private int detailsFlag;
    private int impressionFlag;
    private int cityId;
    private int modelId;
    private int len;

    public String getHotelid() {
        return hotelid;
    }

    public void setHotelid(String hotelid) {
        this.hotelid = hotelid;
    }

    public float getAvg_hotel_br() {
        return avg_hotel_br;
    }

    public void setAvg_hotel_br(float avg_hotel_br) {
        this.avg_hotel_br = avg_hotel_br;
    }

    public float getAvg_hotel_ctr() {
        return avg_hotel_ctr;
    }

    public void setAvg_hotel_ctr(float avg_hotel_ctr) {
        this.avg_hotel_ctr = avg_hotel_ctr;
    }

    public float getAvg_hotel_btod() {
        return avg_hotel_btod;
    }

    public void setAvg_hotel_btod(float avg_hotel_btod) {
        this.avg_hotel_btod = avg_hotel_btod;
    }

    public float getDiscount_per() {
        return discount_per;
    }

    public void setDiscount_per(float discount_per) {
        this.discount_per = discount_per;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public int getFprice() {
        return Fprice;
    }

    public void setFprice(int fprice) {
        Fprice = fprice;
    }

    public int getSprice() {
        return Sprice;
    }

    public void setSprice(int sprice) {
        Sprice = sprice;
    }

    public int getBookingFlag() {
        return bookingFlag;
    }

    public void setBookingFlag(int bookingFlag) {
        this.bookingFlag = bookingFlag;
    }

    public int getDetailsFlag() {
        return this.detailsFlag;
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

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }
}
