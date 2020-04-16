package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeatureBank {
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
    @Autowired
    private ModelSelection modeldecide;
    @Autowired
    private FeatureCreation mfeatures;

    public JSONObject makeFeatures(List<HotelData> featureData) {
        JSONArray arr = new JSONArray();
        JSONObject object = new JSONObject();
        JSONObject jsonRequest = new JSONObject();
        len = featureData.size();
        HotelData a = featureData.get(0);
        cityId = a.getCityId();
        modelId = modeldecide.finalmodel(cityId);
        jsonRequest.put("model", modelId);
        for (int i = 0; i < len; i++) {
            HotelData t = featureData.get(i);
            hotelid = t.getHotelid();
            rating = t.getRating();
            bookingFlag = t.getBookingFlag();
            impressionFlag = t.getImpressionFlag();
            detailsFlag = t.getDetailsFlag();
            ratingCount = t.getRcount();
            Fprice = t.getFprice();
            Sprice = t.getSprice();
            discount_per = mfeatures.discount(Sprice, Fprice);
            avg_hotel_br = mfeatures.br(bookingFlag, impressionFlag);
            avg_hotel_ctr = mfeatures.ctr(detailsFlag, impressionFlag);
            avg_hotel_btod = mfeatures.btod(bookingFlag, detailsFlag);
            System.out.print(avg_hotel_btod);
            arr.add(Fprice);
            arr.add(rating);
            arr.add(ratingCount);
            arr.add(discount_per);
            arr.add(avg_hotel_br);
            arr.add(avg_hotel_ctr);
            arr.add(avg_hotel_btod);
            object.put(hotelid, arr.clone());
            arr.clear();
        }
        jsonRequest.put("evaluation_Data", object);
        return jsonRequest;
    }
}





