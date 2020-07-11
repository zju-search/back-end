package com.search.dto;

import lombok.Data;

@Data
public class PastData {
    double open;
    double close;
    double max;
    double min;
    String time;
    double vol;
    double amount;
}
