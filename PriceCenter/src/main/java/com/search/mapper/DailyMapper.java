package com.search.mapper;

import com.search.model.Daily;
import com.search.model.StockInfo;

import java.util.List;

public interface DailyMapper {

    String GetMaxDate();

    List<StockInfo> SelectByMarket(String market, String date);

    List<StockInfo> SelectAll();

    int insert(Daily record);

    int insertSelective(Daily record);
}