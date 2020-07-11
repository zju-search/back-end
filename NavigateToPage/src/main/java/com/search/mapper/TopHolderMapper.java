package com.search.mapper;

import com.search.model.TopHolder;
import com.search.model.TopHolderInfo;

import java.util.List;

public interface TopHolderMapper {

    List<TopHolderInfo> selectByTscode(String tscode, String year);

    int insert(TopHolder record);

    int insertSelective(TopHolder record);
}