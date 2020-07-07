package com.search.mapper;

import com.search.model.StockList;

public interface StockListMapper {
    int insert(StockList record);

    int insertSelective(StockList record);
}