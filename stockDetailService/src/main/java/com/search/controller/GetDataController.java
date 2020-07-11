package com.search.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.search.dto.*;
import com.search.mapper.PastDataMapper;
import com.search.service.GetTodayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class GetDataController {

    @Autowired
    PastDataMapper pastDataMapper;

    @Autowired
    GetTodayDataService getTodayDataService;

    private final RestTemplate restTemplate;

    public GetDataController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping(value = "/getHistoryData")
    public Message getPastData(@RequestParam("ts_code")String ts_code){
        PastDataListMessage message = new PastDataListMessage();
        List<PastData> pastDataList = new ArrayList<>();

        pastDataList = pastDataMapper.getPastData(ts_code);


        message.setPastDataList(pastDataList);
        message.setState(true);
        message.setMessage("历史数据查询成功");
        return message;
    }

    @PostMapping(value = "/getDataToday")
    public Message getTodayData(@RequestParam("ts_code") String ts_code){
        TodayDataListMessage message = new TodayDataListMessage();

        List<TodayData> todayDataList = new ArrayList<>();
        todayDataList = getTodayDataService.getTodayData(ts_code);

        message.setTodayDataList(todayDataList);
        message.setState(true);
        message.setMessage("查询实时数据成功");
        return message;
    }

}
