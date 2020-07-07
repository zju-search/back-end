package com.search.controller;

import com.search.dto.*;
import com.search.mapper.ConditionMapper;
import com.search.mapper.ScreenerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class FilterController {

    @Autowired
    ScreenerMapper screenerMapper;

    @Autowired
    ConditionMapper conditionMapper;

    @GetMapping(value = "/api/condition")
    public Message getConditionField(@RequestParam("type") String type,
                                     @RequestParam("year") String year)
    {
        ConditionMessage message = new ConditionMessage();
        double minValue,maxValue;

        //排序的依据在daily数据库中
        if(type.equals("pct_chg")){
            minValue = conditionMapper.getMinValueInTradeYear(type,year,"daily");
            maxValue = conditionMapper.getMaxValueInTradeYear(type,year,"daily");
        }
        //排序的依据在daily_basic数据库中
        else if(type.equals("pe_ttm") || type.equals("pe") || type.equals("dv_radio")
                || type.equals("ps") || type.equals("total_mv") || type.equals("pb")
                || type.equals("circ_mv"))
        {
            minValue = conditionMapper.getMinValueInTradeYear(type,year,"daily_basic");
            maxValue = conditionMapper.getMaxValueInTradeYear(type,year,"daily_basic");
        }

        //排序的依据在stock_fina_indicator数据库中
        else if(type.equals("eps") || type.equals("bps") || type.equals("roe")
                || type.equals("netprofit_yoy") || type.equals("profit_to_gr")
                || type.equals("or_yoy") || type.equals("profit_to_op") || type.equals("roa"))
        {
            minValue = conditionMapper.getMinValueInAnnYear(type,year,"fina_indicator");
            maxValue = conditionMapper.getMaxValueInAnnYear(type,year,"fina_indicator");
        }
        else{
            message.setState(false);
            message.setMessage("未定义的参数");
            return message;
        }

        message.setMessage("条件设置成功");
        message.setState(true);
        message.setMaxValue(maxValue);
        message.setMinValue(minValue);
        return message;
    }


    @GetMapping(value = "/api/screener")
    public Message screen(@RequestParam("conditionList") List<Map<String,String>> conditionMapList,
                          @RequestParam("industry") String industry,
                          @RequestParam("area")String area)
    {
        StockListMessage message = new StockListMessage();
        List<String> codeList = new ArrayList<>();
        List<Stock> stockList = new ArrayList<>();

        /*遍历conditionMapList，获得满足所有条件的codeList*/
        for(int i=0;i<conditionMapList.size();i++){
            Map<String,String> conditionMap = conditionMapList.get(i);

            /*若为第一个条件，则进行初步筛选，创建codeList*/
            if(i==0){
                String type = conditionMap.get("type");

                //若筛选的依据在daily数据库中
                if(type.equals("pct_chg"))
                {
                    String year = conditionMap.get("year");
                    double minValue = Double.parseDouble(conditionMap.get("minValue"));
                    double maxValue = Double.parseDouble(conditionMap.get("maxValue"));
                    codeList = screenerMapper.getSuitableCodesInTradeYear(type,year,
                            minValue,maxValue,"daily");

                }

                //若筛选的依据在daily_basic数据库中
                else if(type.equals("pe_ttm") || type.equals("pe") || type.equals("dv_radio")
                        || type.equals("ps") || type.equals("total_mv") || type.equals("pb")
                        || type.equals("circ_mv"))
                {
                    String year = conditionMap.get("year");
                    double minValue = Double.parseDouble(conditionMap.get("minValue"));
                    double maxValue = Double.parseDouble(conditionMap.get("maxValue"));
                    codeList = screenerMapper.getSuitableCodesInTradeYear(type,year,
                            minValue,maxValue,"daily_basic");
                }
                //若筛选的依据在fina_indicator数据库中
                else if(type.equals("eps") || type.equals("bps") || type.equals("roe")
                        || type.equals("netprofit_yoy") || type.equals("profit_to_gr")
                        || type.equals("or_yoy") || type.equals("profit_to_op") || type.equals("roa"))
                {
                    String year = conditionMap.get("year");
                    double minValue = Double.parseDouble(conditionMap.get("minValue"));
                    double maxValue = Double.parseDouble(conditionMap.get("maxValue"));
                    codeList = screenerMapper.getSuitableCodesInAnnYear(type,year,
                            minValue,maxValue,"fina_indicator");
                }


            }
            /*若为后续条件，则对每个stock进行审查，缩小codeList*/
            else{
                //若没有满足条件的stock，则退出
                if(codeList.size()==0) {break;}
                String type = conditionMap.get("type");
                String year = conditionMap.get("year");
                double minValue = Double.parseDouble(conditionMap.get("minValue"));
                double maxValue = Double.parseDouble(conditionMap.get("maxValue"));
                MinMaxValue minMaxValue = new MinMaxValue();//每只股票对应参数的minMaxvalue

                //若筛选的依据在daily数据库中
                if(type.equals("pct_chg"))
                {
                    /*使用迭代器遍历codeList，取出每只股票新条件参数下的min值、max值，若不符合新条件，则删除*/
                    Iterator<String> it = codeList.iterator();
                    String ts_code;
                    while(it.hasNext()){
                        ts_code = it.next();
                        minMaxValue = screenerMapper.getValueInTradeYear(type,ts_code,year,"daily");
                        if(minMaxValue.getMinValue()<minValue || minMaxValue.getMaxValue()>maxValue){
                            it.remove();
                        }
                    }

                }

                //若筛选的依据在daily_basic数据库中
                else if(type.equals("pe_ttm") || type.equals("pe") || type.equals("dv_radio")
                        || type.equals("ps") || type.equals("total_mv") || type.equals("pb")
                        || type.equals("circ_mv"))
                {
                    /*使用迭代器遍历codeList，取出每只股票新条件参数下的min值、max值，若不符合新条件，则删除*/
                    Iterator<String> it = codeList.iterator();
                    String ts_code;
                    while(it.hasNext()){
                        ts_code = it.next();
                        minMaxValue = screenerMapper.getValueInTradeYear(type,ts_code,year,"daily_basic");
                        if(minMaxValue.getMinValue()<minValue || minMaxValue.getMaxValue()>maxValue){
                            it.remove();
                        }
                    }
                }
                //若筛选的依据在fina_indicator数据库中
                else if(type.equals("eps") || type.equals("bps") || type.equals("roe")
                        || type.equals("netprofit_yoy") || type.equals("profit_to_gr")
                        || type.equals("or_yoy") || type.equals("profit_to_op") || type.equals("roa"))
                {
                    /*使用迭代器遍历codeList，取出每只股票新条件参数下的min值、max值，若不符合新条件，则删除*/
                    Iterator<String> it = codeList.iterator();
                    String ts_code;
                    while(it.hasNext()){
                        ts_code = it.next();
                        minMaxValue = screenerMapper.getValueInAnnYear(type,ts_code,year,"fina_indicator");
                        if(minMaxValue.getMinValue()<minValue || minMaxValue.getMaxValue()>maxValue){
                            it.remove();
                        }
                    }

                }

            }


        }

        /*接着，使用迭代器遍历codeList，删除不符合行业和地域信息的ts_code*/
        if(!industry.equals("all")){
            Iterator<String> it = codeList.iterator();
            String ts_code;
            while(it.hasNext()){
                ts_code = it.next();
                if(!screenerMapper.getIndustryByCode(ts_code).equals(industry)){
                    it.remove();
                }
            }
        }
        if(!area.equals("all")){
            Iterator<String> it = codeList.iterator();
            String ts_code;
            while(it.hasNext()){
                ts_code = it.next();
                if(!screenerMapper.getAreaByCode(ts_code).equals(area)){
                    it.remove();
                }
            }
        }

        /*现在，遍历codeList，获取其余信息,，生成stockList*/
        for(int i=0;i<codeList.size();i++){
            String ts_code = codeList.get(i);
            Stock stock = new Stock();
            String latestDate = screenerMapper.getLatestTradeDate();
            stock.setName(screenerMapper.getNameByCode(ts_code));
            stock.setSymbol(screenerMapper.getSymbolByCode(ts_code));
            stock.setClose(screenerMapper.getCloseByCode(ts_code,latestDate));
            stock.setPct_chg(screenerMapper.getPctChgByCode(ts_code,latestDate));
            stockList.add(stock);
        }

        message.setStockList(stockList);
        return message;
    }
}
