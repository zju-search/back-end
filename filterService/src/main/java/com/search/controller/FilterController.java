package com.search.controller;

import com.alibaba.fastjson.JSONObject;
import com.search.dto.*;
import com.search.mapper.ConditionMapper;
import com.search.mapper.ScreenerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class FilterController {

    @Autowired
    ScreenerMapper screenerMapper;

    @Autowired
    ConditionMapper conditionMapper;

    @PostMapping(value = "/condition")
    public Message getConditionField(@RequestParam("type") String type,
                                     @RequestParam("year") String year)
    {
        ConditionMessage message = new ConditionMessage();
        double minValue,maxValue;

        //排序的依据在daily数据库中
        if(type.equals("pct_chg")){
            minValue = conditionMapper.getMinNotFinaValue(type,"new_daily");
            maxValue = conditionMapper.getMaxNotFinaValue(type,"new_daily");
        }
        //排序的依据在daily_basic数据库中
        else if(type.equals("pe_ttm") || type.equals("pe") || type.equals("dv_radio")
                || type.equals("ps") || type.equals("total_mv") || type.equals("pb")
                || type.equals("circ_mv"))
        {
            minValue = conditionMapper.getMinNotFinaValue(type,"new_daily_basic");
            maxValue = conditionMapper.getMaxNotFinaValue(type,"new_daily_basic");

        }

        //排序的依据在stock_fina_indicator数据库中
        else if(type.equals("eps") || type.equals("bps") || type.equals("roe")
                || type.equals("netprofit_yoy") || type.equals("profit_to_gr")
                || type.equals("or_yoy") || type.equals("profit_to_op") || type.equals("roa"))
        {
            minValue = conditionMapper.getMinFinaValue(type+"_min",year);
            maxValue = conditionMapper.getMaxFinaValue(type+"_max",year);
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


    @PostMapping(value = "/screener")
    public Message screen(@RequestParam("conditionList") String conditionListStr,
                          @RequestParam("industry") String industry,
                          @RequestParam("area")String area)
    {
        StockListMessage message = new StockListMessage();
        List<String> codeList = new ArrayList<>();
        List<Stock> stockList = new ArrayList<>();

        List<Condition> conditionList = JSONObject.parseArray(conditionListStr, Condition.class);

        /*遍历conditionMapList，获得满足所有条件的codeList*/
        for(int i=0;i<conditionList.size();i++){
            Condition condition = conditionList.get(i);

            /*若为第一个条件，则进行初步筛选，创建codeList*/
            if(i==0){
                String type = condition.getType();

                //若筛选的依据在daily数据库中
                if(type.equals("pct_chg"))
                {
                    double minValue = condition.getMinValue();
                    double maxValue = condition.getMaxValue();
                    codeList = screenerMapper
                            .getNewSuitableCodes(type,minValue,maxValue,"new_daily");
                }

                //若筛选的依据在daily_basic数据库中
                else if(type.equals("pe_ttm") || type.equals("pe") || type.equals("dv_radio")
                        || type.equals("ps") || type.equals("total_mv") || type.equals("pb")
                        || type.equals("circ_mv"))
                {
                    double minValue = condition.getMinValue();
                    double maxValue = condition.getMaxValue();
                    codeList = screenerMapper
                            .getNewSuitableCodes(type,minValue,maxValue,"new_daily_basic");
                }
                //若筛选的依据在fina_indicator数据库中
                else if(type.equals("eps") || type.equals("bps") || type.equals("roe")
                        || type.equals("netprofit_yoy") || type.equals("profit_to_gr")
                        || type.equals("or_yoy") || type.equals("profit_to_op") || type.equals("roa"))
                {
                    String year = Integer.toString(condition.getYear());
                    double minValue = condition.getMinValue();
                    double maxValue = condition.getMaxValue();
                    if(year.equals("2020")){
                        codeList = screenerMapper
                                .getNewSuitableCodes(type,
                                        minValue,maxValue,"new_fina_indicator");

                    }
                    else{
                        codeList = screenerMapper.getPastSuitableCodes(type,year,minValue,maxValue);
                    }

                }


            }
            /*若为后续条件，则对每个stock进行审查，缩小codeList*/
            else{
                //若没有满足条件的stock，则退出
                if(codeList.size()==0) {break;}
                String type = condition.getType();
                String year = Integer.toString(condition.getYear());
                double minValue = condition.getMinValue();
                double maxValue = condition.getMaxValue();


                //若筛选的依据在daily数据库中
                if(type.equals("pct_chg"))
                {
                    /*使用迭代器遍历codeList，取出每只股票新条件参数下的min值、max值，若不符合新条件，则删除*/
                    Iterator<String> it = codeList.iterator();
                    String ts_code;
                    while(it.hasNext()){
                        ts_code = it.next();
                        double value = screenerMapper.getNotFinaValueByCode(type,ts_code,"new_daily");
                        if( value < minValue || value > maxValue){
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
                        double value = screenerMapper.getNotFinaValueByCode(type,ts_code,"new_daily_basic");
                        if( value < minValue || value > maxValue){
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
                        if(year.equals("2020")){
                            double value = screenerMapper.getNewFinaValueByCode(type,ts_code);
                            if(value < minValue || value > maxValue){
                                it.remove();
                            }
                        }
                        else{
                            double currentMin = screenerMapper.getPastFinaValueByCode(type+"_min",
                                    ts_code,year);
                            double currentMax = screenerMapper.getPastFinaValueByCode(type+"_max",
                                    ts_code,year);
                            if(currentMin<minValue || currentMax>maxValue){
                                it.remove();
                            }
                        }
                    }

                }

            }


        }

        /*对所有codeList，核对area和industry*/
        boolean allArea = area.equals("all");
        boolean allIndustry = industry.equals("all");
        Iterator<String> it = codeList.iterator();
        String ts_code;
        while(it.hasNext()){
            ts_code = it.next();
            //拿数据，做筛选
            StockListInfos stockListInfos = new StockListInfos();
            StockDailyInfos stockDailyInfos = new StockDailyInfos();
            stockListInfos = screenerMapper.getStockListInfoByCode(ts_code);
            stockDailyInfos = screenerMapper.getStockDailyInfosByCode(ts_code);

            System.out.println(stockListInfos);
            System.out.println(stockDailyInfos);

            if(!allArea && !stockListInfos.getArea().equals(area)){
                System.out.println("Area"+area);
                System.out.println("RealArea"+stockListInfos.getArea());
                continue;
            }

            if(!allIndustry && !stockListInfos.getIndustry().equals(industry)){
                System.out.println("Industry"+area);
                System.out.println("RealIndustry"+stockListInfos.getIndustry());
                continue;
            }

            //若符合条件，加入到stockList中
            Stock stock = new Stock();
            stock.setSymbol(stockListInfos.getSymbol());
            stock.setName(stockListInfos.getName());
            if(stockDailyInfos!=null){
                stock.setClose((stockDailyInfos.getClose()));
                stock.setPct_chg(stockDailyInfos.getPct_chg());
            }
            else{
                stock.setClose("0.0");
                stock.setPct_chg("0.0");
            }


            stockList.add(stock);
        }

//        /*接着，使用迭代器遍历codeList，删除不符合行业和地域信息的ts_code*/
//        if(!industry.equals("all")){
//            Iterator<String> it = codeList.iterator();
//            String ts_code;
//            while(it.hasNext()){
//                ts_code = it.next();
//                if(!screenerMapper.getIndustryByCode(ts_code).equals(industry)){
//                    it.remove();
//                }
//            }
//        }
//        if(!area.equals("all")){
//            Iterator<String> it = codeList.iterator();
//            String ts_code;
//            while(it.hasNext()){
//                ts_code = it.next();
//                if(!screenerMapper.getAreaByCode(ts_code).equals(area)){
//                    it.remove();
//                }
//            }
//        }
//
//        /*现在，遍历codeList，获取其余信息,，生成stockList*/
//        for(int i=0;i<codeList.size();i++){
//            String ts_code = codeList.get(i);
//            Stock stock = new Stock();
//
//
//            stock.setName(screenerMapper.getNameByCode(ts_code));
//            stock.setSymbol(screenerMapper.getSymbolByCode(ts_code));
//            stock.setClose(screenerMapper.getCloseByCode(ts_code));
//            stock.setPct_chg(screenerMapper.getPctChgByCode(ts_code));
//
//            stockList.add(stock);
//        }

        message.setState(true);
        message.setStockList(stockList);
        return message;
    }







}

