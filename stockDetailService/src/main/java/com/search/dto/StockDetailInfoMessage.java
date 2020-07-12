package com.search.dto;

import lombok.Data;

@Data
public class StockDetailInfoMessage extends Message{
    StockDetailInfo stocks;
    String introduction;
    String main_business;
}
