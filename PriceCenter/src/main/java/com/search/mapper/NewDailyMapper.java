package com.search.mapper;

import com.search.model.NewDaily;

import java.util.List;

public interface NewDailyMapper {
    List<NewDaily> selectAll();

    int insert(NewDaily record);

    int insertSelective(NewDaily record);
}