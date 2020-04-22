package com.example.demo.Config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.IndicesExists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration

public class ESConfiguration {

    @Bean
    public JestClient jestClient()
    {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder("http://localhost:9200").
                        multiThreaded(true).
                        defaultMaxTotalConnectionPerRoute(2).
                        maxTotalConnection(10).
                        build());
        JestClient jestClient= factory.getObject();
        Map<String,Object> setting = new HashMap<>();
        setting.put("number_of_shards", 3);
        setting.put("number_of_replicas",2);
        try {
            jestClient.execute(new CreateIndex.Builder("hotelid").settings(setting).build());
        }
        catch (Exception e){
            System.out.println(e.toString()+ "----------build unsuccessful---------");
        }
        return  jestClient;
    }


}
