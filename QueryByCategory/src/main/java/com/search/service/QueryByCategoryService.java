package com.search.service;

import com.search.model.StockInfo;

import java.util.List;

public interface QueryByCategoryService {

    List<StockInfo> SelectByMarket(String market);
}
