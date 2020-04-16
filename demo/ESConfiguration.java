package com.example.demo;

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
    public static JestClient jestClient() throws IOException{
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig.Builder("http://localhost:9200")
                        .multiThreaded(true)
                        .defaultMaxTotalConnectionPerRoute(2)
                        .maxTotalConnection(10)
                        .build());
        JestClient jestClient =  factory.getObject();
        JestResult result = jestClient.execute(new IndicesExists.Builder("hotels").build());
        if(!result.isSucceeded()) {
            System.out.println(result.getErrorMessage());
        }
        return jestClient;
    }


}
