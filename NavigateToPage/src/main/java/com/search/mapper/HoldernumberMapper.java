package com.search.mapper;

import com.search.model.HNInfo;
import com.search.model.Holdernumber;

import java.util.List;

public interface HoldernumberMapper {
    List<HNInfo> selectByTscode(String tscode);

    int insert(Holdernumber record);

    int insertSelective(Holdernumber record);
}