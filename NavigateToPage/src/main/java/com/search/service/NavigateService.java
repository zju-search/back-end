package com.search.service;

import com.alibaba.fastjson.JSONObject;

public interface NavigateService {
    JSONObject performanceBroad(String symbol);

    JSONObject shareHolderNum(String symbol);

    JSONObject tenShareHolder(String symbol, String year);

    JSONObject TigerDragon();

    JSONObject getPledgeData(String symbol);
}
