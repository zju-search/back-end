package com.search.controller;

import com.search.dto.Message;
import com.search.dto.RankedObject;
import com.search.dto.RankedObjectListMessage;
import com.search.mapper.LeaderboardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class LeadorboardController {
    @Autowired
    LeaderboardMapper leaderboardMapper;

    //股票排行榜
    @PostMapping(value = "/stockRanking")
    public Message stockRanking(@RequestParam("type" )String type,
                                @RequestParam("reverse")boolean reverse)

    {
        RankedObjectListMessage message = new RankedObjectListMessage();

        List<RankedObject> rankedObjectList = new ArrayList<RankedObject>();

        if(reverse == false){
            //排序的依据在daily数据库中
            if(type.equals("pct_chg")){
                rankedObjectList = leaderboardMapper
                        .getTopTwentyStockOnNotFina(type,"new_daily");

                for(int i=0;i<rankedObjectList.size();i++){
                    RankedObject rankedObject = rankedObjectList.get(i);
                    String ts_code = rankedObject.getTs_code();
                    String name = leaderboardMapper.getNameByCode(ts_code);
                    String symbol = leaderboardMapper.getSymbolByCode(ts_code);
                    String closeStr = leaderboardMapper.getCloseByCode(ts_code);
                    double close = 0;
                    if(closeStr !=null){
                        close = Double.parseDouble(closeStr);
                    }


                    rankedObject.setName(name);
                    rankedObject.setSymbol(symbol);
                    rankedObject.setClose(close);

                    rankedObjectList.set(i,rankedObject);
                }
            }
            //排序的依据在daily_basic数据库中
            else if(type.equals("pe_ttm") || type.equals("pe") || type.equals("dv_ratio")
                    || type.equals("ps") || type.equals("total_mv") || type.equals("pb")
                    || type.equals("circ_mv"))
            {


                rankedObjectList = leaderboardMapper
                        .getTopTwentyStockOnNotFina(type,"new_daily_basic");

                for(int i=0;i<rankedObjectList.size();i++){
                    RankedObject rankedObject = rankedObjectList.get(i);
                    String ts_code = rankedObject.getTs_code();
                    String name = leaderboardMapper.getNameByCode(ts_code);
                    String symbol = leaderboardMapper.getSymbolByCode(ts_code);
                    String closeStr = leaderboardMapper.getCloseByCode(ts_code);
                    double close = 0;
                    if(closeStr !=null){
                        close = Double.parseDouble(closeStr);
                    }

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
                        .getTopTwentyStockOnFina(type);

                for(int i=0;i<rankedObjectList.size();i++){
                    RankedObject rankedObject = rankedObjectList.get(i);
                    String ts_code = rankedObject.getTs_code();
                    String name = leaderboardMapper.getNameByCode(ts_code);
                    String symbol = leaderboardMapper.getSymbolByCode(ts_code);
                    String closeStr = leaderboardMapper.getCloseByCode(ts_code);
                    double close = 0;
                    if(closeStr !=null){
                        close = Double.parseDouble(closeStr);
                    }

                    rankedObject.setName(name);
                    rankedObject.setSymbol(symbol);
                    rankedObject.setClose(close);

                    rankedObjectList.set(i,rankedObject);
                }
            }



        }

        else{
            //排序的依据在daily数据库中
            if(type.equals("pct_chg")){
                rankedObjectList = leaderboardMapper
                        .getBottomTwentyStockOnNotFina(type,"new_daily");

                for(int i=0;i<rankedObjectList.size();i++){
                    RankedObject rankedObject = rankedObjectList.get(i);
                    String ts_code = rankedObject.getTs_code();
                    String name = leaderboardMapper.getNameByCode(ts_code);
                    String symbol = leaderboardMapper.getSymbolByCode(ts_code);
                    String closeStr = leaderboardMapper.getCloseByCode(ts_code);
                    double close = 0;
                    if(closeStr !=null){
                        close = Double.parseDouble(closeStr);
                    }


                    rankedObject.setName(name);
                    rankedObject.setSymbol(symbol);
                    rankedObject.setClose(close);

                    rankedObjectList.set(i,rankedObject);
                }
            }
            //排序的依据在daily_basic数据库中
            else if(type.equals("pe_ttm") || type.equals("pe") || type.equals("dv_ratio")
                    || type.equals("ps") || type.equals("total_mv") || type.equals("pb")
                    || type.equals("circ_mv"))
            {


                rankedObjectList = leaderboardMapper
                        .getBottomTwentyStockOnNotFina(type,"new_daily_basic");

                for(int i=0;i<rankedObjectList.size();i++){
                    RankedObject rankedObject = rankedObjectList.get(i);
                    String ts_code = rankedObject.getTs_code();
                    String name = leaderboardMapper.getNameByCode(ts_code);
                    String symbol = leaderboardMapper.getSymbolByCode(ts_code);
                    String closeStr = leaderboardMapper.getCloseByCode(ts_code);
                    double close = 0;
                    if(closeStr !=null){
                        close = Double.parseDouble(closeStr);
                    }

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
                        .getBottomTwentyStockOnFina(type);

                for(int i=0;i<rankedObjectList.size();i++){
                    RankedObject rankedObject = rankedObjectList.get(i);
                    String ts_code = rankedObject.getTs_code();
                    String name = leaderboardMapper.getNameByCode(ts_code);
                    String symbol = leaderboardMapper.getSymbolByCode(ts_code);
                    String closeStr = leaderboardMapper.getCloseByCode(ts_code);
                    double close = 0;
                    if(closeStr !=null){
                        close = Double.parseDouble(closeStr);
                    }

                    rankedObject.setName(name);
                    rankedObject.setSymbol(symbol);
                    rankedObject.setClose(close);

                    rankedObjectList.set(i,rankedObject);
                }
            }



        }

        message.setRankedObjectList(rankedObjectList);
        message.setState(true);
        return message;
    }



}
