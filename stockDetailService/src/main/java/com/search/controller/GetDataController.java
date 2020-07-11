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

    @PostMapping(value = "/getHistoryData")
    public Message getPastData(@RequestParam("symbol")String symbol){
        PastDataListMessage message = new PastDataListMessage();
        List<PastData> pastDataList = new ArrayList<>();

        String ts_code;
        if(symbol.compareTo("400000")<0){
            ts_code = symbol+".SZ";
        }
        else{
            ts_code = symbol +".SH";
        }
        pastDataList = pastDataMapper.getPastData(ts_code);

        for(PastData pastData:pastDataList){
            String trade_date = pastData.getTrade_date();
            String time = trade_date.substring(0,4)+"-"+trade_date.substring(4,6)
                    +"-"+trade_date.substring(6);
            pastData.setTime(time);
        }


        message.setPastDataList(pastDataList);
        message.setState(true);
        message.setMessage("历史数据查询成功");
        return message;
    }

    @PostMapping(value = "/getDataToday")
    public Message getTodayData(@RequestParam("symbol") String symbol){
        TodayDataListMessage message = new TodayDataListMessage();

        List<TodayData> todayDataList = new ArrayList<>();
        String ts_code = "000300.SH";

        todayDataList = getTodayDataService.getTodayData(ts_code);

        message.setTodayDataList(todayDataList);
        message.setState(true);
        message.setMessage("查询实时数据成功");
        return message;
    }

}
