package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Handler {
    @Autowired
    private ESRepository esrepository;
    @Autowired
    private Ranking templateController;
    @Autowired
    private FeatureBank f;
    @Autowired
    private RedisRepository rep;
    @Autowired
    private SetDataElasticSearch data;

    public List<RequestFormat> processInput(RequestFormat hotelid) throws IOException {
        RequestFormat value = new RequestFormat();
        List<HotelData> list1 = new ArrayList<>();
        List<String> list = new ArrayList<>();
        List<String> existInRedis = new ArrayList<>();
        List<String> NotExistInRedis = new ArrayList<>();
        List<String> listofhotels = new ArrayList<>();
        value.setCityId(hotelid.getCityId());
        data.setValues();
        listofhotels = hotelid.getHotelIds();
        for (int i = 0; i < listofhotels.size(); i++) {
            if (rep.existsById(listofhotels.get(i))) {
                existInRedis.add(listofhotels.get(i));

            } else
                NotExistInRedis.add(listofhotels.get(i));
        }

        list1 = ((List<HotelData>) rep.findAllById(existInRedis));
        list1.addAll(esrepository.getDataByQuery(NotExistInRedis));

        JSONObject obj = new JSONObject();
        obj = f.makeFeatures(list1);
        list = templateController.callFlask(obj);
        value.setHotelIds(list);
        return (List<RequestFormat>) value;
    }

}
