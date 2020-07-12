package com.search.controller;

import com.alibaba.fastjson.JSONObject;
import com.search.model.StockInfo;
import com.search.model.StockList;
import com.search.service.QueryByCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class QueryByCategoryController {

    @Resource
    QueryByCategoryService queryByCategoryService;

    @RequestMapping(value = "/getStocksbyMarket", method = RequestMethod.POST)
    public JSONObject getStockByMarket(@RequestParam(name = "market_index") int market) {

        return queryByCategoryService.SelectByMarket(market);
    }
}
