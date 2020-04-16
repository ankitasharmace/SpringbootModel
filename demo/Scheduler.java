package com.example.demo;

import io.searchbox.client.JestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class Scheduler {
    @Autowired
    private JestClient jestClient;
    @Autowired
    private ESRepository es;
    @Autowired
    private RedisRepository repo;


    @Scheduled(fixedRate = 10000000)
    public void fixedRateSch() {
        List<HotelData> hotelslist = null;
        try {
            hotelslist = es.getAllHotels();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            repo.saveAll(hotelslist);
        }
        catch (Exception e) {

        }
    }

}

