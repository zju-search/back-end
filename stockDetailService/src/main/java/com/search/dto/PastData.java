package com.search.dto;

import lombok.Data;

@Data
public class PastData {
    double open;
    double close;
    double max;
    double min;
    String trade_date;
    String time;
    double vol;
    double amount;
}
