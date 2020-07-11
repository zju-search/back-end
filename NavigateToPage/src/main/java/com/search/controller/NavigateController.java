package com.search.controller;

import com.alibaba.fastjson.JSONObject;
import com.search.model.StockInfo;
import com.search.model.TigerInfo;
import com.search.service.NavigateService;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@EnableDiscoveryClient
@EnableFeignClients
@CrossOrigin(origins = "*",maxAge = 3600)
public class NavigateController {

    @Resource
    NavigateService navigateService;

    @RequestMapping(value = "/shareHolderNum", method = RequestMethod.POST)
    public JSONObject shareHolderNum(@RequestParam(name = "symbol") String symbol) {
        return navigateService.shareHolderNum(symbol);
    }

    @RequestMapping(value = "/tenShareHolder", method = RequestMethod.POST)
    public JSONObject tenShareHolder(@RequestParam(name = "symbol") String symbol,
                                     @RequestParam(name = "year") String year) {
        return navigateService.tenShareHolder(symbol, year);
    }

    @RequestMapping(value = "/performanceBroad", method = RequestMethod.POST)
    public JSONObject performanceBroad(@RequestParam(name = "ts_code") String symbol) {
        return navigateService.performanceBroad(symbol);
    }

    @RequestMapping(value = "/TigerDragon", method = RequestMethod.POST)
    public JSONObject TigerDragon() {
        return navigateService.TigerDragon();
    }

    @RequestMapping(value = "/getPledgeData", method = RequestMethod.POST)
    public JSONObject getPledgeData(@RequestParam(name = "ts_code") String symbol) {
        return navigateService.getPledgeData(symbol);
    }
}
