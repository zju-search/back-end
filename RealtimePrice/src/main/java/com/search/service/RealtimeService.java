package com.search.service;

import com.alibaba.fastjson.JSONObject;
import com.search.model.Price;
import com.search.model.Realtime;
import com.search.model.TigerInfo;

import java.util.List;

public interface RealtimeService {

    List<Realtime> getAll();

    List<Price> getIndex(String tscode);

    List<TigerInfo> getTigerDragon();
}
