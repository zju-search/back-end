package com.search.service;

import com.search.mapper.DailyBasicMapper;
import com.search.mapper.DailyMapper;
import com.search.mapper.StockListMapper;
import com.search.model.Daily;
import com.search.model.DailyBasic;
import com.search.model.StockInfo;
import com.search.model.StockList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class QueryByCategoryServiceImpl implements QueryByCategoryService{

    @Resource
    StockListMapper stockListMapper;
    @Resource
    DailyMapper dailyMapper;
    @Resource
    DailyBasicMapper dailyBasicMapper;

    @Override
    public List<StockInfo> SelectByMarket(String market){

        ArrayList<StockInfo> stockInfoList = new ArrayList<>();

        for (StockList singleStock : stockListMapper.SelectByMarket(market)) {

            StockInfo tempStockInfo = new StockInfo();
            tempStockInfo.setSymbol(singleStock.getSymbol());
            tempStockInfo.setName(singleStock.getName());

            stockInfoList.add(tempStockInfo);
        }

        return stockInfoList;
    }

}
