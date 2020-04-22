package com.example.demo.Repository;

import com.example.demo.Entities.hotelData;
import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class ESRepository {
    @Autowired
    private JestClient jestClient;

    public void setValues() throws IOException {

        hotelData hotelinfo = new hotelData();
        hotelinfo.setHotelid("ghi");
        hotelinfo.setLatitude("28.6129");
        hotelinfo.setLongitude("77.2295");
        hotelinfo.setSprice(2000);
        hotelinfo.setFprice(1000);
        hotelinfo.setRating(3);
        hotelinfo.setRcount(50);
        hotelinfo.setDisplayCategory("Home");
        hotelinfo.setBookingFlag(1);
        hotelinfo.setDetailsFlag(5);
        hotelinfo.setImpressionFlag(10);
        hotelinfo.setCityId(2);
        hotelData hotelinfo1 = new hotelData();
        hotelinfo1.setHotelid("jkl");
        hotelinfo1.setLatitude("28.6129");
        hotelinfo1.setLongitude("77.2295");
        hotelinfo1.setSprice(3000);
        hotelinfo1.setFprice(1000);
        hotelinfo1.setRating(5);
        hotelinfo1.setRcount(100);
        hotelinfo1.setDisplayCategory("Home");
        hotelinfo1.setBookingFlag(2);
        hotelinfo1.setDetailsFlag(6);
        hotelinfo1.setImpressionFlag(7);
        hotelinfo1.setCityId(1);
        jestClient.execute(new Bulk.Builder()
                .defaultIndex("hotelid")
                .defaultType("k")
                .addAction(new Index.Builder(hotelinfo).build())
                .addAction(new Index.Builder(hotelinfo1).build())
                .build());
    }

    public List<hotelData> getAllHotels() throws IOException {

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(10000);
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex("hotelids")
                .build();
        SearchResult searchResult = jestClient.execute(search);
        List<SearchResult.Hit<hotelData, Void>> searchHits = searchResult.getHits(hotelData.class);
        List<hotelData> results = searchHits.stream()
                .map(h -> h.source)
                .collect(Collectors.toList());

        return results;
    }

    public List<hotelData> getDataByQuery(List<String> hotelIds) throws IOException {
            String queryDSL = "{"+
                    " \"query\":{"+
                    " \"terms\":"+
                    "{ \"hotelid\": [";
            for(String x:hotelIds){
                queryDSL = queryDSL+"\""+x+"\",";
            }
            queryDSL = queryDSL.substring(0,queryDSL.length()-1);
            queryDSL=queryDSL+"]}}}";
            List<hotelData> result = new LinkedList<>();
            try {
                result = jestClient.execute(new Search.Builder(queryDSL).addIndex("hotelid").addType("k").build()).getSourceAsObjectList(hotelData.class);

            }
            catch (Exception e){

                System.out.println(e);
                return result;
            }
            return result;
        }

}

