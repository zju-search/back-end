package com.search.controller;

import com.alibaba.fastjson.JSONObject;
import com.search.service.PriceCenterService;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableDiscoveryClient
@EnableFeignClients
@CrossOrigin(origins = "*",maxAge = 3600)
public class PriceCenterController {

    @Resource
    PriceCenterService priceCenterService;

    @RequestMapping(value = "/getStocks", method = RequestMethod.POST)
    public JSONObject getStocks() {
        return priceCenterService.GetAllInfo();
    }
}
