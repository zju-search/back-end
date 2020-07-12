package com.search.mapper;

import com.search.model.Price;
import com.search.model.Realtime;

import java.util.List;

public interface RealtimeMapper {
    List<Realtime> selectAll();

    List<Price> selectIndex(String tscode);

    int insert(Realtime record);

    int insertSelective(Realtime record);
}