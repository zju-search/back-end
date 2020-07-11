package com.search.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.search.mapper.ExpressMapper;
import com.search.mapper.HoldernumberMapper;
import com.search.mapper.PledgeStatMapper;
import com.search.mapper.TopHolderMapper;
import com.search.model.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class NavigateServiceImpl implements NavigateService {

    @Resource
    HoldernumberMapper holdernumberMapper;
    @Resource
    ExpressMapper expressMapper;
    @Resource
    TopHolderMapper topHolderMapper;
    @Resource
    PledgeStatMapper pledgeStatMapper;

    private final RestTemplate restTemplate;

    public NavigateServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public JSONObject shareHolderNum(String symbol) {
        JSONObject res = new JSONObject();

        List<HNInfo> holdernumberList = holdernumberMapper.selectByTscode(symbol + "%");
        res.put("HolderNumList", holdernumberList);

        return res;
    }

    @Override
    public JSONObject tenShareHolder(String symbol, String year) {
        JSONObject res = new JSONObject();
        int i;
        List<TopHolderInfo> now = topHolderMapper.selectByTscode(symbol + "%", year + "1231");
        if (now.size() == 0) {
            res.put("holderList", null);
            return res;
        }

        int last_year = Integer.parseInt(year) - 1;
        List<TopHolderInfo> last = topHolderMapper.selectByTscode(symbol + "%", last_year + "1231");

        Collections.sort(now, new Comparator<TopHolderInfo>() {
            @Override
            public int compare(TopHolderInfo u1, TopHolderInfo u2) {
                long f = Long.parseLong(u1.getHold_amount());
                long s = Long.parseLong(u2.getHold_amount());
                if (f > s) {
                    return -1;
                } else if (f < s) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (i = 0; i < now.size(); i++) {
            TopHolderInfo now_temp = now.get(i);
            now_temp.setLast_change("新进股东");
            String name_temp = now_temp.getHolder_name();
            double now_amount = Double.parseDouble(now_temp.getHold_amount());
            if (now_amount > 100000000) {
                now_temp.setHold_amount(String.format("%.2f", now_amount / 100000000) + "亿");
            } else if (now_amount > 10000) {
                now_temp.setHold_amount((int) (now_amount / 10000) + "万");
            }
            if (last.size() == 0) {
                continue;
            }
            for (TopHolderInfo last_temp : last) {
                if (name_temp.equals(last_temp.getHolder_name())) {
                    double last_amount = Double.parseDouble(last_temp.getHold_amount());
                    double pctChg = (now_amount - last_amount) / last_amount * 100;
                    now_temp.setLast_change(String.format("%.2f", pctChg) + "%");
                    break;
                }
            }
        }
        res.put("holderList", now);
        return res;
    }

    @Override
    public JSONObject performanceBroad(String symbol) {
        JSONObject res = new JSONObject();
        int i;
        String[] title = {"股票代码", "公告日期", "报告期", "营业收入(万元)", "营业利润(万元)", "利润总额(万元)", "净利润(万元)", "总资产(万元)", "股东权益合计(不含少数股东权益)(万元)"
        , "每股收益(摊薄)(元)", "净资产收益率(摊薄)(%)", "去年同期修正后净利润", "每股净资产", "业绩简要说明"};

        Express expressList = expressMapper.selectByTscode(symbol + "%");

        ArrayList<JSONObject> pledgeList = new ArrayList<>();
        NumberFormat nf = NumberFormat.getInstance();
        DecimalFormat df=(DecimalFormat)NumberFormat.getInstance();
        df.setMaximumFractionDigits(2);
        nf.setGroupingUsed(false);
        if (expressList == null) {
            res.put("prediction", null);
            return res;
        }
        for (i = 0; i < 12; i++) {
            JSONObject temp = new JSONObject();
            temp.put("title", title[i]);
            if (i == 0) {
                temp.put("string", expressList.getTsCode());
            } else if (i == 1) {
                temp.put("string", expressList.getAnnDate());
            } else if (i == 2) {
                temp.put("string", expressList.getEndDate());
            } else if (i == 3) {
                temp.put("string", df.format(expressList.getRevenue() / 10000));
            } else if (i == 4) {
                temp.put("string", df.format(expressList.getOperateProfit() / 10000));
            } else if (i == 5) {
                temp.put("string", df.format(expressList.getTotalProfit() / 10000));
            } else if (i == 6) {
                temp.put("string", df.format(expressList.getNIncome() / 10000));
            } else if (i == 7) {
                temp.put("string", df.format(expressList.getTotalAssets() / 10000));
            } else if (i == 8){
                temp.put("string", df.format(expressList.getTotalHldrEqyExcMinInt() / 10000));
            } else if (i == 9){
                temp.put("string", nf.format(expressList.getDilutedEps()));
            } else if (i == 10){
                temp.put("string", nf.format(expressList.getDilutedRoe()));
            } else {
                temp.put("string", df.format(expressList.getYoyNetProfit()));
            }
            pledgeList.add(temp);
        }
        res.put("prediction", pledgeList);
        return res;
    }

    @Override
    public JSONObject TigerDragon() {
        JSONObject res = new JSONObject();
        JSONArray TigerDragon = restTemplate.getForObject("http://47.115.28.231:8092/getTigerDragon", JSONArray.class);
        List<StockInfo> TigerList = JSONObject.parseArray(TigerDragon.toJSONString(), StockInfo.class);
        res.put("chg_list", null);
        res.put("turnover_list", null);
        if (TigerList.size() >= 10) {
            res.put("chg_list", TigerList.subList(0, 5));
            res.put("turnover_list", TigerList.subList(5, 10));
        }
        return res;
    }

    @Override
    public JSONObject getPledgeData(String symbol) {
        JSONObject res = new JSONObject();
        int i;

        String[] title = {"股票代码", "截止日期", "质押次数", "无限售股质押数量（万）", "限售股份质押数量（万）", "总股本", "质押比例"};

        PledgeStatInfo pledgeStatInfo = pledgeStatMapper.selectByTscode(symbol + "%");
        if (pledgeStatInfo == null) {
            res.put("pledge", null);
            return res;
        }
        ArrayList<JSONObject> pledgeList = new ArrayList<>();

        for (i = 0; i < 7; i++) {
            JSONObject temp = new JSONObject();
            temp.put("title", title[i]);
            if (i == 0) {
                temp.put("string", pledgeStatInfo.getTsCode());
            } else if (i == 1) {
                temp.put("string", pledgeStatInfo.getEndDate());
            } else if (i == 2) {
                temp.put("string", pledgeStatInfo.getPledgeCount());
            } else if (i == 3) {
                temp.put("string", pledgeStatInfo.getUnrestPledge());
            } else if (i == 4) {
                temp.put("string", pledgeStatInfo.getRestPledge());
            } else if (i == 5) {
                temp.put("string", pledgeStatInfo.getTotalShare());
            } else {
                temp.put("string", pledgeStatInfo.getPledgeCount());
            }
            pledgeList.add(temp);
        }

        res.put("pledge", pledgeList);

        return res;
    }

}
