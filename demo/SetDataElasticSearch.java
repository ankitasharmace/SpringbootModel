package com.example.demo;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class SetDataElasticSearch {
    @Autowired
    private JestClient jestClient;
    public void setValues() throws IOException {
        JestResult result=jestClient.execute(new CreateIndex.Builder("hotels").build());
        if(!result.isSucceeded()) {
            System.out.println(result.getErrorMessage());
        }
        jestClient.execute(new IndicesExists.Builder("hotels").build());
        if(!result.isSucceeded()) {
            System.out.println(result.getErrorMessage());
        }

        jestClient.execute(new CreateIndex.Builder("hotelid").build());
        HotelData hotelinfo = new HotelData();
        hotelinfo.setHotelid("abc");
        hotelinfo.setLatitude("28.6129");
        hotelinfo.setLongitude("77.2295");
        hotelinfo.setSprice(2000);
        hotelinfo.setFprice(1000);
        hotelinfo.setRating(3);
        hotelinfo.setRcount(50);
        hotelinfo.setDisplayCategory("Home");
        hotelinfo.setBookingFlag(1);
        hotelinfo.setDetailsFlag(5);
        hotelinfo.setImpressionFlag(10);
        hotelinfo.setCityId(2);
        HotelData hotelinfo1 = new HotelData();
        hotelinfo1.setHotelid("def");
        hotelinfo1.setLatitude("28.6129");
        hotelinfo1.setLongitude("77.2295");
        hotelinfo1.setSprice(3000);
        hotelinfo1.setFprice(1000);
        hotelinfo1.setRating(5);
        hotelinfo1.setRcount(100);
        hotelinfo1.setDisplayCategory("Home");
        hotelinfo1.setBookingFlag(2);
        hotelinfo1.setDetailsFlag(6);
        hotelinfo1.setImpressionFlag(7);
        hotelinfo1.setCityId(1);
        jestClient.execute(new Bulk.Builder()
                .defaultIndex("hotelid")
                .addAction(new Index.Builder(hotelinfo).build())
                .addAction(new Index.Builder(hotelinfo1).build())
                .build());
    }
}
