package com.search.dto;

import lombok.Data;

@Data
public class RankedObject {

    private String name;
    private String ts_code;
    private String symbol;
    private double close;
    private double value;
}
