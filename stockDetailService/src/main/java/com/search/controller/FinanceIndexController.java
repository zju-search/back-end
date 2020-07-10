package com.search.controller;

import com.search.dto.FinanceIndex;
import com.search.dto.FinanceIndexListMessage;
import com.search.dto.FinanceIndices;
import com.search.dto.Message;
import com.search.mapper.FinanceIndexMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class FinanceIndexController {

    @Autowired
    FinanceIndexMapper financeIndexMapper;

    @PostMapping(value = "/financeIndex")
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


        FinanceIndices[] results = new FinanceIndices[10];
        String ts_code = financeIndexMapper.getCodeBySymbol(symbol);

        results = financeIndexMapper.getFinanceIndicesByCode(ts_code);

        double _2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010;

        /*下述代码虽然表述麻烦，但是处理时间短*/
        _2010=results[0].getProfit_to_op();_2011=results[1].getProfit_to_op();
        _2012=results[2].getProfit_to_op();_2013=results[3].getProfit_to_op();
        _2014=results[4].getProfit_to_op();_2015=results[5].getProfit_to_op();
        _2016=results[6].getProfit_to_op();_2017=results[7].getProfit_to_op();
        _2018=results[8].getProfit_to_op();_2019=results[9].getProfit_to_op();
        financeIndexList.add(new FinanceIndex(titles[0],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getOr_yoy();_2011=results[1].getOr_yoy();
        _2012=results[2].getOr_yoy();_2013=results[3].getOr_yoy();
        _2014=results[4].getOr_yoy();_2015=results[5].getOr_yoy();
        _2016=results[6].getOr_yoy();_2017=results[7].getOr_yoy();
        _2018=results[8].getOr_yoy();_2019=results[9].getOr_yoy();
        financeIndexList.add(new FinanceIndex(titles[1],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getProfit_to_gr();_2011=results[1].getProfit_to_gr();
        _2012=results[2].getProfit_to_gr();_2013=results[3].getProfit_to_gr();
        _2014=results[4].getProfit_to_gr();_2015=results[5].getProfit_to_gr();
        _2016=results[6].getProfit_to_gr();_2017=results[7].getProfit_to_gr();
        _2018=results[8].getProfit_to_gr();_2019=results[9].getProfit_to_gr();
        financeIndexList.add(new FinanceIndex(titles[2],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getNetprofit_yoy();_2011=results[1].getNetprofit_yoy();
        _2012=results[2].getNetprofit_yoy();_2013=results[3].getNetprofit_yoy();
        _2014=results[4].getNetprofit_yoy();_2015=results[5].getNetprofit_yoy();
        _2016=results[6].getNetprofit_yoy();_2017=results[7].getNetprofit_yoy();
        _2018=results[8].getNetprofit_yoy();_2019=results[9].getNetprofit_yoy();
        financeIndexList.add(new FinanceIndex(titles[3],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getProfit_dedt();_2011=results[1].getProfit_dedt();
        _2012=results[2].getProfit_dedt();_2013=results[3].getProfit_dedt();
        _2014=results[4].getProfit_dedt();_2015=results[5].getProfit_dedt();
        _2016=results[6].getProfit_dedt();_2017=results[7].getProfit_dedt();
        _2018=results[8].getProfit_dedt();_2019=results[9].getProfit_dedt();
        financeIndexList.add(new FinanceIndex(titles[4],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getEps();_2011=results[1].getEps();
        _2012=results[2].getEps();_2013=results[3].getEps();
        _2014=results[4].getEps();_2015=results[5].getEps();
        _2016=results[6].getEps();_2017=results[7].getEps();
        _2018=results[8].getEps();_2019=results[9].getEps();
        financeIndexList.add(new FinanceIndex(titles[5],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getInvest_capital();_2011=results[1].getInvest_capital();
        _2012=results[2].getInvest_capital();_2013=results[3].getInvest_capital();
        _2014=results[4].getInvest_capital();_2015=results[5].getInvest_capital();
        _2016=results[6].getInvest_capital();_2017=results[7].getInvest_capital();
        _2018=results[8].getInvest_capital();_2019=results[9].getInvest_capital();
        financeIndexList.add(new FinanceIndex(titles[6],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getCapital_rese_ps();_2011=results[1].getCapital_rese_ps();
        _2012=results[2].getCapital_rese_ps();_2013=results[3].getCapital_rese_ps();
        _2014=results[4].getCapital_rese_ps();_2015=results[5].getCapital_rese_ps();
        _2016=results[6].getCapital_rese_ps();_2017=results[7].getCapital_rese_ps();
        _2018=results[8].getCapital_rese_ps();_2019=results[9].getCapital_rese_ps();
        financeIndexList.add(new FinanceIndex(titles[7],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getUndist_profit_ps();_2011=results[1].getUndist_profit_ps();
        _2012=results[2].getUndist_profit_ps();_2013=results[3].getUndist_profit_ps();
        _2014=results[4].getUndist_profit_ps();_2015=results[5].getUndist_profit_ps();
        _2016=results[6].getUndist_profit_ps();_2017=results[7].getUndist_profit_ps();
        _2018=results[8].getUndist_profit_ps();_2019=results[9].getUndist_profit_ps();
        financeIndexList.add(new FinanceIndex(titles[8],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getOcfps();_2011=results[1].getOcfps();
        _2012=results[2].getOcfps();_2013=results[3].getOcfps();
        _2014=results[4].getOcfps();_2015=results[5].getOcfps();
        _2016=results[6].getOcfps();_2017=results[7].getOcfps();
        _2018=results[8].getOcfps();_2019=results[9].getOcfps();
        financeIndexList.add(new FinanceIndex(titles[9],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getRoe();_2011=results[1].getRoe();
        _2012=results[2].getRoe();_2013=results[3].getRoe();
        _2014=results[4].getRoe();_2015=results[5].getRoe();
        _2016=results[6].getRoe();_2017=results[7].getRoe();
        _2018=results[8].getRoe();_2019=results[9].getRoe();
        financeIndexList.add(new FinanceIndex(titles[10],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getRoe_yoy();_2011=results[1].getRoe_yoy();
        _2012=results[2].getRoe_yoy();_2013=results[3].getRoe_yoy();
        _2014=results[4].getRoe_yoy();_2015=results[5].getRoe_yoy();
        _2016=results[6].getRoe_yoy();_2017=results[7].getRoe_yoy();
        _2018=results[8].getRoe_yoy();_2019=results[9].getRoe_yoy();
        financeIndexList.add(new FinanceIndex(titles[11],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getRoa();_2011=results[1].getRoa();
        _2012=results[2].getRoa();_2013=results[3].getRoa();
        _2014=results[4].getRoa();_2015=results[5].getRoa();
        _2016=results[6].getRoa();_2017=results[7].getRoa();
        _2018=results[8].getRoa();_2019=results[9].getRoa();
        financeIndexList.add(new FinanceIndex(titles[12],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getGrossprofit_margin();_2011=results[1].getGrossprofit_margin();
        _2012=results[2].getGrossprofit_margin();_2013=results[3].getGrossprofit_margin();
        _2014=results[4].getGrossprofit_margin();_2015=results[5].getGrossprofit_margin();
        _2016=results[6].getGrossprofit_margin();_2017=results[7].getGrossprofit_margin();
        _2018=results[8].getGrossprofit_margin();_2019=results[9].getGrossprofit_margin();
        financeIndexList.add(new FinanceIndex(titles[13],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getNetprofit_margin();_2011=results[1].getNetprofit_margin();
        _2012=results[2].getNetprofit_margin();_2013=results[3].getNetprofit_margin();
        _2014=results[4].getNetprofit_margin();_2015=results[5].getNetprofit_margin();
        _2016=results[6].getNetprofit_margin();_2017=results[7].getNetprofit_margin();
        _2018=results[8].getNetprofit_margin();_2019=results[9].getNetprofit_margin();
        financeIndexList.add(new FinanceIndex(titles[14],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getDebt_to_assets();_2011=results[1].getDebt_to_assets();
        _2012=results[2].getDebt_to_assets();_2013=results[3].getDebt_to_assets();
        _2014=results[4].getDebt_to_assets();_2015=results[5].getDebt_to_assets();
        _2016=results[6].getDebt_to_assets();_2017=results[7].getDebt_to_assets();
        _2018=results[8].getDebt_to_assets();_2019=results[9].getDebt_to_assets();
        financeIndexList.add(new FinanceIndex(titles[15],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getCurrent_ratio();_2011=results[1].getCurrent_ratio();
        _2012=results[2].getCurrent_ratio();_2013=results[3].getCurrent_ratio();
        _2014=results[4].getCurrent_ratio();_2015=results[5].getCurrent_ratio();
        _2016=results[6].getCurrent_ratio();_2017=results[7].getCurrent_ratio();
        _2018=results[8].getCurrent_ratio();_2019=results[9].getCurrent_ratio();
        financeIndexList.add(new FinanceIndex(titles[16],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getQuick_ratio();_2011=results[1].getQuick_ratio();
        _2012=results[2].getQuick_ratio();_2013=results[3].getQuick_ratio();
        _2014=results[4].getQuick_ratio();_2015=results[5].getQuick_ratio();
        _2016=results[6].getQuick_ratio();_2017=results[7].getQuick_ratio();
        _2018=results[8].getQuick_ratio();_2019=results[9].getQuick_ratio();
        financeIndexList.add(new FinanceIndex(titles[17],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getAssets_to_eqt();_2011=results[1].getAssets_to_eqt();
        _2012=results[2].getAssets_to_eqt();_2013=results[3].getAssets_to_eqt();
        _2014=results[4].getAssets_to_eqt();_2015=results[5].getAssets_to_eqt();
        _2016=results[6].getAssets_to_eqt();_2017=results[7].getAssets_to_eqt();
        _2018=results[8].getAssets_to_eqt();_2019=results[9].getAssets_to_eqt();
        financeIndexList.add(new FinanceIndex(titles[18],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getDebt_to_eqt();_2011=results[1].getDebt_to_eqt();
        _2012=results[2].getDebt_to_eqt();_2013=results[3].getDebt_to_eqt();
        _2014=results[4].getDebt_to_eqt();_2015=results[5].getDebt_to_eqt();
        _2016=results[6].getDebt_to_eqt();_2017=results[7].getDebt_to_eqt();
        _2018=results[8].getDebt_to_eqt();_2019=results[9].getDebt_to_eqt();
        financeIndexList.add(new FinanceIndex(titles[19],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getEqt_yoy();_2011=results[1].getEqt_yoy();
        _2012=results[2].getEqt_yoy();_2013=results[3].getEqt_yoy();
        _2014=results[4].getEqt_yoy();_2015=results[5].getEqt_yoy();
        _2016=results[6].getEqt_yoy();_2017=results[7].getEqt_yoy();
        _2018=results[8].getEqt_yoy();_2019=results[9].getEqt_yoy();
        financeIndexList.add(new FinanceIndex(titles[20],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getOcf_yoy();_2011=results[1].getOcf_yoy();
        _2012=results[2].getOcf_yoy();_2013=results[3].getOcf_yoy();
        _2014=results[4].getOcf_yoy();_2015=results[5].getOcf_yoy();
        _2016=results[6].getOcf_yoy();_2017=results[7].getOcf_yoy();
        _2018=results[8].getOcf_yoy();_2019=results[9].getOcf_yoy();
        financeIndexList.add(new FinanceIndex(titles[21],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getTurn_days();_2011=results[1].getTurn_days();
        _2012=results[2].getTurn_days();_2013=results[3].getTurn_days();
        _2014=results[4].getTurn_days();_2015=results[5].getTurn_days();
        _2016=results[6].getTurn_days();_2017=results[7].getTurn_days();
        _2018=results[8].getTurn_days();_2019=results[9].getTurn_days();
        financeIndexList.add(new FinanceIndex(titles[22],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getAssets_turn();_2011=results[1].getAssets_turn();
        _2012=results[2].getAssets_turn();_2013=results[3].getAssets_turn();
        _2014=results[4].getAssets_turn();_2015=results[5].getAssets_turn();
        _2016=results[6].getAssets_turn();_2017=results[7].getAssets_turn();
        _2018=results[8].getAssets_turn();_2019=results[9].getAssets_turn();
        financeIndexList.add(new FinanceIndex(titles[23],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getAr_turn();_2011=results[1].getAr_turn();
        _2012=results[2].getAr_turn();_2013=results[3].getAr_turn();
        _2014=results[4].getAr_turn();_2015=results[5].getAr_turn();
        _2016=results[6].getAr_turn();_2017=results[7].getAr_turn();
        _2018=results[8].getAr_turn();_2019=results[9].getAr_turn();
        financeIndexList.add(new FinanceIndex(titles[24],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getCa_turn();_2011=results[1].getCa_turn();
        _2012=results[2].getCa_turn();_2013=results[3].getCa_turn();
        _2014=results[4].getCa_turn();_2015=results[5].getCa_turn();
        _2016=results[6].getCa_turn();_2017=results[7].getCa_turn();
        _2018=results[8].getCa_turn();_2019=results[9].getCa_turn();
        financeIndexList.add(new FinanceIndex(titles[25],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));

        _2010=results[0].getFa_turn();_2011=results[1].getFa_turn();
        _2012=results[2].getFa_turn();_2013=results[3].getFa_turn();
        _2014=results[4].getFa_turn();_2015=results[5].getFa_turn();
        _2016=results[6].getFa_turn();_2017=results[7].getFa_turn();
        _2018=results[8].getFa_turn();_2019=results[9].getFa_turn();
        financeIndexList.add(new FinanceIndex(titles[26],_2019,_2018,_2017,_2016,_2015,
                _2014,_2013,_2012,_2011,_2010));




/*
        for(int i=0;i<27;i++){
            double[] results = new double[10];
            double _2019,_2018,_2017,_2016,_2015,
                    _2014,_2013,_2012,_2011,_2010;

            results = financeIndexMapper.getFinanceIndicesByCode(ts_code,parameters[i]);
            _2010 = results[0];_2011 = results[1];_2012 = results[2];_2013 = results[3];
            _2014 = results[4];_2015 = results[5];_2016 = results[6];
            _2017 = results[7];_2018 = results[8];_2019 = results[9];

            financeIndexList.add(new FinanceIndex(titles[i],_2019,_2018,_2017,_2016,_2015,
                    _2014,_2013,_2012,_2011,_2010));
        }
*/
        message.setFinanceIndexList(financeIndexList);
        message.setState(true);
        return message;
    }


}
