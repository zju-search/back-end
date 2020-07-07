package com.search.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockInfo implements Serializable {

    private String ts_code;

    private String name;

    private double current_price;

    private double change;

    private double amount;

    private double close;

    private double pct_chg;


    private static final long serialVersionUID = 1L;
}