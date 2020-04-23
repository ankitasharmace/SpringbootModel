package com.example.demo.Model;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ModelSelection {
    public int getModel(int cityId){
        Map<Integer,Integer> hm = new HashMap<>();

        hm.put(1, 10);
        hm.put(2, 7);
        hm.put(3, 9);
        int picklemodel;
        picklemodel=hm.get(cityId);
        return picklemodel;
    }
}
