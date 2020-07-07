package com.search.mapper;

import com.search.model.StockList;

import java.util.List;

public interface StockListMapper {

    List<StockList> SelectByMarket(String market);

    int insert(StockList record);

    int insertSelective(StockList record);
}