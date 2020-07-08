package com.search.service;

import com.alibaba.fastjson.JSONObject;
import com.search.model.StockInfo;

import java.util.List;

public interface QueryByCategoryService {

    JSONObject SelectByMarket(int market);
}
