package com.search.service;

import com.alibaba.fastjson.JSONObject;

public interface QueryByCategoryService {

    JSONObject SelectByMarket(int market);
}
