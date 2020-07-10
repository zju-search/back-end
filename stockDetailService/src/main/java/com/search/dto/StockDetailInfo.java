package com.search.dto;

import lombok.Data;

@Data
public class StockDetailInfo {
    String symbol;
    String name;
    double current_price;
    double close;
    String open;
    String high;
    String low;
    double change;
    double pct_chg;
    double vol;
    double amount;
    double pe_ttm;
    double pe;
    double circ_mv;
    double total_mv;
    double turnover_rate;
    double dv_ratio;
    double dv_ttm;
    double total_share;
    double float_share;
}
