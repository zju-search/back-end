package com.search.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockInfo implements Serializable {

    private String tsCode;

    private String name;

    private double currentPrice;

    private double change;

    private double amount;

    private double close;

    private double pctChg;

    private static final long serialVersionUID = 1L;
}