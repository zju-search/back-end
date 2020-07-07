package com.search.controller;

import com.alibaba.fastjson.JSONObject;
import com.search.service.PriceCenterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class PriceCenterController {

    @Resource
    PriceCenterService priceCenterService;

    @RequestMapping(value = "/getStocks", method = RequestMethod.GET)
    public JSONObject getStocks() {
        return priceCenterService.GetAllInfo();
    }
}
