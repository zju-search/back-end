package com.search.mapper;

import com.search.model.PledgeStat;
import com.search.model.PledgeStatInfo;

import java.util.List;

public interface PledgeStatMapper {
    PledgeStatInfo selectByTscode(String tscode);

    int insert(PledgeStat record);

    int insertSelective(PledgeStat record);
}