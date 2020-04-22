package com.example.demo.Model;

import com.example.demo.Entities.RequestFormat;
import com.example.demo.Repository.ESRepository;
import com.example.demo.ScorePrediction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class Ranking {
    @Autowired
    private ESRepository esrepository;
    @Autowired
    private Ranking templateController;
    @Autowired
    private Features features;
    @Autowired
    private ModelSelection modelselection;
    @Autowired
    private ScorePrediction scorepredictor;

    public List<String> processInput(RequestFormat hotelid) throws IOException {
        int cityId=hotelid.getCityId();
        int modelId=modelselection.getModel(cityId);
        List<String> hotellist=new ArrayList<>();
        hotellist=hotelid.getHotelIds();
        HashMap<String, List<Double>> hotels = features.makeFeatures(hotellist,modelId);
        List<String> sortedHotels = scorepredictor.getScoreFromFlask(modelId,hotels);
        return sortedHotels;
    }
}
