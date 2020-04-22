package com.example.demo.Cronjob;


import com.example.demo.Entities.hotelData;
import com.example.demo.Repository.ESRepository;
//import com.example.demo.Repository.RedisRepository;
import io.searchbox.client.JestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

//@Component
public class Scheduler  {

//    @Autowired
//    private ESRepository es;
//   @Autowired
//    private RedisRepository redisrepo;
//
//
//
//    @Scheduled(fixedRate = 10000000)
//    public void fixedRateSch()  throws IOException {
//        List<hotelData> hotellist = null;
//        try {
//            hotellist = es.getAllHotels();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//       try {
//         redisrepo.saveAll(hotellist);
//       }
//        catch (Exception e) {
//        }
//   }

}

