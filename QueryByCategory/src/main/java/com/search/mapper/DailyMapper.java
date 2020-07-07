package com.search.mapper;

import com.search.model.Daily;

public interface DailyMapper {

    Daily selectByTscode(String tscode);

    int insert(Daily record);

    int insertSelective(Daily record);
}