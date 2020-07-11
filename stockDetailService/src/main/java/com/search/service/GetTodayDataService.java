package com.search.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.search.dto.TodayData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GetTodayDataService {

    private final RestTemplate restTemplate;

    public GetTodayDataService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<TodayData> getTodayData(String ts_code){


        String jsonStr = "http://47.115.28.231:8092/getIndex/"+ts_code;
        //000300.SH
        JSONArray indices = restTemplate.getForObject(jsonStr, JSONArray.class);

        List<TodayData> priceList = JSONObject.parseArray(indices.toJSONString(), TodayData.class);

        System.out.println(priceList.get(0));
        if(priceList.get(0).getDtime() == null){
            System.out.println("priceList.get(0).getTime() is null");
        }
        if (indices.size() == 1 && priceList.get(0).getDtime().equals("out of date")) {
            return null;
        }
        else{
            return priceList;
        }

    }
}
