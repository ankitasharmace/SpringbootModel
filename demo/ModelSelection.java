package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ModelSelection {
    public int finalmodel(int cityId){
        Map<Integer,Integer> hm = new HashMap<>();


        hm.put(1, 10);
        hm.put(2, 7);
        hm.put(3, 9);
        int picklemodel;
        picklemodel=hm.get(cityId);
        return picklemodel;
    }
}
