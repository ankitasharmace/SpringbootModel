package com.example.demo.Model;

import com.example.demo.Entities.hotelData;
import com.example.demo.Entities.hotelFeature;
import com.example.demo.Repository.ESRepository;
import com.example.demo.Repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class Features {
    @Autowired
    private ModelSelection modeldecide;
    @Autowired
    private FeatureCreation mfeatures;
    @Autowired
    private ESRepository esrepository;
    @Autowired
    private RedisRepository redisrepository;

    private List<String> hotel;
    private int model;

    public HashMap<String, List<Double>> makeFeatures(List<String> hotel,int model) throws IOException {
        this.hotel = hotel;
        this.model=model;
        HashMap<String,List<Double>> requestMap = new HashMap<>();
        List<String> hotelsNotInRedis=new ArrayList<>();
        if (model==10){
//            DATA IS FED INTO REDIS ONLY THROUGH SCHEDULER THIS CODE WILL WORK WHEN REDIS HAS DATA
//           List<hotelData> hotelDataInRedis=redisrepository.findAllById(hotel);
//            for(int i=0;i<hotel.size();i++) {
//                if (!(hotelDataInRedis.get(i).getSprice())) {
//                    hotelsInRedis.add(hotelDataInRedis.get(i).getHotelid());
//                }
//            }
//            List<hotelData> hotelDataNotInRedis=esrepository.getDataByQuery(hotelsNotInRedis);
//            featureData=hotelDataNotInRedis+hotelDataInRedis

//            So in this code , we are fetching data from es directly.
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
        return requestMap;
    }
}








