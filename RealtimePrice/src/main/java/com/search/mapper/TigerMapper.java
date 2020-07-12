package com.search.mapper;

import com.search.model.Tiger;
import com.search.model.TigerInfo;

import java.util.List;

public interface TigerMapper {
    List<TigerInfo> selectByPCT();

    List<TigerInfo> selectByTurnover();

    int insert(Tiger record);

    int insertSelective(Tiger record);
}