package com.search.mapper;

import com.search.model.DailyBasic;

public interface DailyBasicMapper {
    DailyBasic selectByTscode(String tscode);

    int insert(DailyBasic record);

    int insertSelective(DailyBasic record);
}