package com.search.controller;

import com.search.dto.FinanceIndex;
import com.search.dto.FinanceIndexListMessage;
import com.search.dto.Message;
import com.search.mapper.FinanceIndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FinanceIndexController {

    @Autowired
    FinanceIndexMapper financeIndexMapper;

    @GetMapping(value = "/api/financeIndex")
    public Message getFinanceIndex(@RequestParam("symbol") String symbol)
    {
        FinanceIndexListMessage message = new FinanceIndexListMessage();
        List<FinanceIndex> financeIndexList = new ArrayList<>();

        String[] titles = {"营业收入","营业收入同比增长","净利润","净利润同比增长","扣非净利润",
                "每股收益","每股净资产","每股资本公积金","每股未分配利润","每股经营现金流",
                "净资产收益率","净资产收益率-摊薄","总资产报酬率","销售毛利率","销售净利率",
                "资产负债率","流动比率","速动比率","权益乘数","产权比率",
                "归属股东权益相对年初增长率","现金流量净额同比增长率", "营业周期",
                "总资产周转率","应收账款周转率","流动资产周转率","固定资产周转率"};
        String[] parameters = {"profit_to_op","or_yoy","profit_to_gr","netprofit_yoy","profit_dedt",
                "eps","invest_capital","capital_rese_ps","undist_profit_ps","ocfps",
                "roe","roe_yoy","roa","grossprofit_margin","netprofit_margin",
                "debt_to_assets","current_ratio","quick_ratio","assets_to_eqt","debt_to_eqt",
                "eqt_yoy","ocf_yoy","turn_days",
                "assets_turn","ar_turn","ca_turn","fa_turn"};

        String ts_code = financeIndexMapper.getCodeBySymbol(symbol);

        for(int i=0;i<27;i++){
            double _2019,_2018,_2017,_2016,_2015,_2014,_2013,_2012,_2011,_2010;
            _2019 = financeIndexMapper.getFinanceIndexByCode(ts_code,parameters[i],"2019");
            _2018 = financeIndexMapper.getFinanceIndexByCode(ts_code,parameters[i],"2018");
            _2017 = financeIndexMapper.getFinanceIndexByCode(ts_code,parameters[i],"2017");
            _2016 = financeIndexMapper.getFinanceIndexByCode(ts_code,parameters[i],"2016");
            _2015 = financeIndexMapper.getFinanceIndexByCode(ts_code,parameters[i],"2015");
            _2014 = financeIndexMapper.getFinanceIndexByCode(ts_code,parameters[i],"2014");
            _2013 = financeIndexMapper.getFinanceIndexByCode(ts_code,parameters[i],"2013");
            _2012 = financeIndexMapper.getFinanceIndexByCode(ts_code,parameters[i],"2012");
            _2011 = financeIndexMapper.getFinanceIndexByCode(ts_code,parameters[i],"2011");
            _2010 = financeIndexMapper.getFinanceIndexByCode(ts_code,parameters[i],"2010");

            financeIndexList.add(new FinanceIndex(titles[i],_2019,_2018,_2017,_2016,_2015,
                    _2014,_2013,_2012,_2011,_2010));
        }

        message.setFinanceIndexList(financeIndexList);
        message.setState(true);
        return message;
    }


}
