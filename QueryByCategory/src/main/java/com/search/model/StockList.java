package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * stock_list
 * @author zcc
 */
@Data
public class StockList implements Serializable {
    private Long index;

    private String tsCode;

    private String symbol;

    private String name;

    private String area;

    private String industry;

    private String listDate;

    private static final long serialVersionUID = 1L;
}