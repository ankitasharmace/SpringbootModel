package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@Service
public class ScorePrediction {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "predict")
    public List<String> getScoreFromFlask(int model, HashMap<String, List<Double>> map) throws JsonProcessingException {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("model", model);
        requestMap.put("evaluation_Data", map);
        JSONObject jsonObject = new JSONObject(requestMap);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), headers);
        System.out.println(request);

        String scores = restTemplate.exchange("http://127.0.0.1:5000/predict", HttpMethod.POST, request, String.class).getBody();

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Double> Response = mapper.readValue(scores, Map.class);
        try {
            List<Map.Entry<String, Double> > list =
                    new LinkedList<Map.Entry<String, Double> >(Response.entrySet());


            Collections.sort(list, new Comparator<Map.Entry<String,Double> >() {
                public int compare(Map.Entry<String, Double> o1,
                                   Map.Entry<String, Double> o2)
                {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });


            List<String> sortedHotelList = new ArrayList<>();
            for(Map.Entry<String,Double> m : list){
                sortedHotelList.add(m.getKey());
            }

            return sortedHotelList;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<String>();
    }

}




