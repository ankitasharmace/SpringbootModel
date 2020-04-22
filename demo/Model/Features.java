package com.example.demo.Model;


import com.example.demo.Entities.hotelData;
import com.example.demo.Entities.hotelFeature;
import com.example.demo.Model.FeatureCreation;
import com.example.demo.Model.ModelSelection;
import com.example.demo.Repository.ESRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Features {

    @Autowired
    private ModelSelection modeldecide;
    @Autowired
    private FeatureCreation mfeatures;
    @Autowired
    private ESRepository esrepository;

    private List<String> hotel;
    private int model;

    public HashMap<String, List<Double>> makeFeatures(List<String> hotel,int model) throws IOException {
        this.hotel = hotel;
        this.model=model;
        HashMap<String,List<Double>> requestMap = new HashMap<>();

        if (model==10){
        List<hotelData> featureData=esrepository.getDataByQuery(hotel);
        int len=featureData.size();
        List<Double> hotelList=new ArrayList<>();
        for (int i = 0; i < len; i++) {
            hotelData t = featureData.get(i);
            hotelFeature features=new hotelFeature();
            features.setHotelid(t.getHotelid());
            features.setRating(t.getRating());
            features.setBookingFlag(t.getBookingFlag());
            features.setImpressionFlag(t.getImpressionFlag());
            features.setDetailsFlag(t.getDetailsFlag());
            features.setRatingCount(t.getRcount());
            features.setFprice(t.getFprice());
            features.setSprice(t.getSprice());
            features.setDiscount_per( mfeatures.discount(t.getSprice(), t.getFprice()));
            features.setAvg_hotel_br( mfeatures.br(t.getBookingFlag(), t.getImpressionFlag()));
            features.setAvg_hotel_ctr(mfeatures.ctr(t.getDetailsFlag(), t.getImpressionFlag()));
            features.setAvg_hotel_btod( mfeatures.btod(t.getBookingFlag(), t.getDetailsFlag()));
            hotelList.add((double) features.getFprice());
            hotelList.add((double) features.getRating());
            hotelList.add((double) features.getRatingCount());
            hotelList.add((double) features.getDiscount_per());
            hotelList.add((double) features.getAvg_hotel_br());
            hotelList.add((double) features.getAvg_hotel_btod());
            hotelList.add((double) features.getAvg_hotel_ctr());
            List<Double> FeatureList=new ArrayList<>();
            FeatureList.addAll(hotelList);
            requestMap.put(t.getHotelid(),FeatureList);
            hotelList.clear();
        }

        return requestMap;

        }
        else{System.out.println("noo");}
        return requestMap;

    }
}








