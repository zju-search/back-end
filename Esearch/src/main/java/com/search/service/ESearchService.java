package com.search.service;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class ESearchService {
    public ArrayList<String> Search(String index, String param, String value) throws IOException {
        ArrayList<String> results = new ArrayList<>();
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(RestClient.builder(new HttpHost("121.89.223.225", 9200, "http")));
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(param, value);
        searchSourceBuilder.query(termQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            String sourceAsString = hit.getSourceAsString();
            HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap = JSON.parseObject(sourceAsString, hashMap.getClass());
            String s = "{"+"\"title\":"+"\""+hashMap.get("title")+"\""+","
                    +"\"content\":"+"\""+hashMap.get("content")+"\""+"}";
            results.add(s);
        }
        return results;
    }
}
