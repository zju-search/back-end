package com.search.dto;


import lombok.Data;

@Data
public class Stock {
    String name;
    String symbol;
    String close;
    String pct_chg;
}
