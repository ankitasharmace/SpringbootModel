package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;

import java.util.List;
public class RequestFormat {
    @Id
    private int cityId;
    private List<String> hotelIds;

    public List<String> getHotelIds() {
        return hotelIds;
    }

    public void setHotelIds(List<String> hotelIds) {
        this.hotelIds = hotelIds;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }


}
