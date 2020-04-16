package com.example.demo;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Repository
public class ESRepository {
    @Autowired
    private JestClient jestClient;

    public List<HotelData> getAllHotels() throws IOException {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(10000);
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("1")
                .build();
        SearchResult searchResult = jestClient.execute(search);
        List<SearchResult.Hit<HotelData, Void>> searchHits = searchResult.getHits(HotelData.class);
        List<HotelData> results = searchHits.stream()
                .map(h -> h.source)
                .collect(Collectors.toList());

        return results;
    }

    public List<HotelData> getDataByQuery(List<String> list) throws IOException {
        String queryDSL = "{"+
                " \"query\":{"+
                " \"terms\":"+
                "{ \"hotelid\": [";
        for(String x:list){
            queryDSL = queryDSL+"\""+x+"\",";
        }
        queryDSL = queryDSL.substring(0,queryDSL.length()-1);
        queryDSL=queryDSL+"]}}}";
        List<HotelData> p = new LinkedList<>();
        try {
            JestResult lol = jestClient.execute(new Search.Builder(queryDSL).build());
            p = lol.getSourceAsObjectList(HotelData.class);
        }
        catch (Exception e){
            throw e;
        }
        return p;
    }
}

