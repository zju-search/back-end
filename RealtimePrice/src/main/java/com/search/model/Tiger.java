package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * new_daily_top_list
 * @author 
 */
@Data
public class Tiger implements Serializable {
    private String tradeDate;

    private String tsCode;

    private String name;

    private Double close;

    private Double pctChange;

    private Double turnoverRate;

    private Double amount;

    private Double lSell;

    private Double lBuy;

    private Double lAmount;

    private Double netAmount;

    private Double netRate;

    private Double amountRate;

    private Double floatValues;

    private String reason;

    private static final long serialVersionUID = 1L;
}