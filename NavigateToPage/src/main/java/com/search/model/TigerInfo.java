package com.search.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TigerInfo implements Serializable {
    private List<StockInfo> chg_list;

    private List<StockInfo> turnover_list;

    private static final long serialVersionUID = 1L;
}
