package com.search.controller;

import com.search.dto.ConditionMessage;
import com.search.dto.Message;
import com.search.mapper.ConditionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilterController {

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

}
