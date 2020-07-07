package com.search.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockInfo implements Serializable {

    private String symbol;

    private String name;

    private double current_price;

    private double change;

    private double amount;

    private double pe_ttm;

    private double total_mv;

    private static final long serialVersionUID = 1L;
}