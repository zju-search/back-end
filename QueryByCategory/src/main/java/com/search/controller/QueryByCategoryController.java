package com.search.controller;

import com.search.model.StockInfo;
import com.search.model.StockList;
import com.search.service.QueryByCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QueryByCategoryController {

    @Resource
    QueryByCategoryService queryByCategoryService;

    @RequestMapping(value = "/getStocksbyMarket", method = RequestMethod.GET)
    public List<StockInfo> getStockByMarket(@RequestParam(name = "market") String market) {

        return queryByCategoryService.SelectByMarket(market);
    }
}
