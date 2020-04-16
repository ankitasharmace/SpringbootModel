package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static java.util.stream.Collectors.toMap;



@Service
public class Ranking {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "predict")
    public List<String> callFlask(JSONObject flaskapicall) {
        Map<String,Double> mapResponse=new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(flaskapicall,headers);
        String  scores = restTemplate.exchange("http://localhost:5000/predict",
                HttpMethod.POST,
                httpEntity,
                String.class).getBody();
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapResponse = mapper.readValue(scores, Map.class);
            System.out.print(mapResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LinkedHashMap<String, Double> sorted = new LinkedHashMap<>();
        sorted = mapResponse
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        List<String> l = new ArrayList<String>(sorted.keySet());
        return l;
        }
    }




