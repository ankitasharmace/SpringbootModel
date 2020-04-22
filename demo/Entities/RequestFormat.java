package com.example.demo.Entities;

import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import java.util.List;

@Entity
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
