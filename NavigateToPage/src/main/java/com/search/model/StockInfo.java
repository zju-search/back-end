package com.search.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class StockInfo implements Serializable {
    private String name;
    private String ts_code;
    private static final long serialVersionUID = 1L;
}
