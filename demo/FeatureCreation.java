package com.example.demo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class FeatureCreation {
    private float avg_hotel_br;
    private float avg_hotel_ctr;
    private float avg_hotel_btod;
    private float discount_per;


    public  float discount(int sprice,int fprice) {
        discount_per = ((sprice - fprice) / sprice) * 100;
        return discount_per;
    }
    public  float br(int bookingFlag,int impressionFlag){
        avg_hotel_br=((bookingFlag)/impressionFlag)*100;
        return avg_hotel_br;
    }

    public  float ctr(int detailsFlag,int impressionFlag ){
        avg_hotel_ctr=(((detailsFlag)/impressionFlag)*100);
        return avg_hotel_ctr;
    }

    public  float btod(int bookingFlag,int detailsFlag){
        avg_hotel_btod=(((bookingFlag)/detailsFlag)*100);
        return avg_hotel_btod;
    }
}
