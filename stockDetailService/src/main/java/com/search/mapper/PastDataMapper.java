package com.search.mapper;

import com.search.dto.PastData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PastDataMapper {


    @Select("select trade_date as time,open,close,high as max,low as min,vol,amount " +
            "from daily " +
            "where ts_code = #{ts_code} and trade_date >= '20200101'  ")
    List<PastData> getPastData(String ts_code);
}
