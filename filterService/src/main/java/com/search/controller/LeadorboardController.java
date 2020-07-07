package com.search.controller;

import com.search.dto.Message;
import com.search.dto.RankedObject;
import com.search.dto.RankedObjectListMessage;
import com.search.mapper.LeaderboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class LeadorboardController {
    @Autowired
    LeaderboardMapper leaderboardMapper;

    //股票排行榜
    @GetMapping(value = "/api/stockRanking")
    public Message stockRanking(@RequestParam("type" )String type,
                                @RequestParam("reverse")boolean reverse)

    {
        RankedObjectListMessage message = new RankedObjectListMessage();

        List<RankedObject> rankedObjectList = new ArrayList<RankedObject>();

        //排序的依据在daily数据库中
        if(type.equals("pct_chg")){
            String latestDate = leaderboardMapper.getLatestTradeDate("daily");
            rankedObjectList = leaderboardMapper
                    .getTopTwentyStockOnTradeDate(latestDate,type,"daily");

            for(int i=0;i<rankedObjectList.size();i++){
                RankedObject rankedObject = rankedObjectList.get(i);
                String ts_code = rankedObject.getTs_code();
                //long index = rankedObject.getIndex();
                String name = leaderboardMapper.getNameByCode(ts_code);
                String symbol = leaderboardMapper.getSymbolByCode(ts_code);
                latestDate = leaderboardMapper.getLatestTradeDate("daily");
                Double close = leaderboardMapper.getCloseByCode(ts_code,latestDate);
              //  Double value = leaderboardMapper.getValueByIndex(type,"daily",index);
               // System.out.println("value = "+value);

                rankedObject.setName(name);
                rankedObject.setSymbol(symbol);
                rankedObject.setClose(close);

                rankedObjectList.set(i,rankedObject);
            }
        }
        //排序的依据在stock_daily_basic数据库中
        else if(type.equals("pe_ttm") || type.equals("pe") || type.equals("dv_radio")
                || type.equals("ps") || type.equals("total_mv") || type.equals("pb")
                || type.equals("circ_mv"))
        {

            String latestDate = leaderboardMapper.getLatestTradeDate("daily_basic");
            rankedObjectList = leaderboardMapper
                    .getTopTwentyStockOnTradeDate(latestDate,type,"daily_basic");

            for(int i=0;i<rankedObjectList.size();i++){
                RankedObject rankedObject = rankedObjectList.get(i);
                String ts_code = rankedObject.getTs_code();
                String name = leaderboardMapper.getNameByCode(ts_code);
                String symbol = leaderboardMapper.getSymbolByCode(ts_code);
                latestDate = leaderboardMapper.getLatestTradeDate("daily");
                Double close = leaderboardMapper.getCloseByCode(ts_code,latestDate);

                rankedObject.setName(name);
                rankedObject.setSymbol(symbol);
                rankedObject.setClose(close);

                rankedObjectList.set(i,rankedObject);
            }
        }

        //排序的依据在stock_fina_indicator数据库中
        else if(type.equals("eps") || type.equals("bps") || type.equals("roe")
            || type.equals("netprofit_yoy") || type.equals("profit_to_gr")
            || type.equals("or_yoy") || type.equals("profit_to_op") || type.equals("roa"))
        {
            String latestDate;
            rankedObjectList = leaderboardMapper
                    .getTopTwentyStock(type,"fina_indicator");

            for(int i=0;i<rankedObjectList.size();i++){
                RankedObject rankedObject = rankedObjectList.get(i);
                String ts_code = rankedObject.getTs_code();
                String name = leaderboardMapper.getNameByCode(ts_code);
                String symbol = leaderboardMapper.getSymbolByCode(ts_code);
                latestDate = leaderboardMapper.getLatestTradeDate("daily");
                Double close = leaderboardMapper.getCloseByCode(ts_code,latestDate);

                rankedObject.setName(name);
                rankedObject.setSymbol(symbol);
                rankedObject.setClose(close);

                rankedObjectList.set(i,rankedObject);
            }
        }

        //下面判断是否要逆序
        if(reverse==true){
            RankedObject preRankedObject,postRankedObject;
            for(int i=0;i<(rankedObjectList.size()-1)/2;i++){
                preRankedObject = rankedObjectList.get(i);
                postRankedObject = rankedObjectList.get(rankedObjectList.size()-1-i);

                rankedObjectList.set(i,postRankedObject);
                rankedObjectList.set(rankedObjectList.size()-1-i,preRankedObject);
            }
        }


        message.setRankedObjectList(rankedObjectList);
        message.setState(true);
        return message;
    }



}
