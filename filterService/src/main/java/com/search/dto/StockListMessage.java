package com.search.dto;

import lombok.Data;

import java.util.List;

@Data
public class StockListMessage extends Message{
    List<Stock> stockList;
}
