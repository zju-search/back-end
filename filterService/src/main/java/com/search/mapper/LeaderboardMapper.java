package com.search.mapper;


import com.search.dto.RankedObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeaderboardMapper {

    @Select("select max(trade_date) from ${tableName}")
    String getLatestTradeDate(String tableName);

    @Select("select max(ann_date) from ${tableName}")
    String getLatestAnnDate(String tableName);

    @Select("select ts_code,${parameter} as value from ${tableName} " +
            "where trade_date = #{trade_date} order by ${parameter} desc limit 20")
    List<RankedObject> getTopTwentyStockOnTradeDate(String trade_date,String parameter,String tableName);


    @Select("select ts_code,max(${parameter}) as value from ${tableName}" +
            "group by ts_code order by max(${parameter}) desc limit 20")
    List<RankedObject> getTopTwentyStock(String parameter,String tableName);

    @Select("select close from daily where ts_code = #{ts_code} and trade_date = #{trade_date}")
    double getCloseByCode(String ts_code,String trade_date);

    @Select("select name from stock_list where ts_code = #{ts_code}")
    String getNameByCode(String ts_code);

    @Select("select symbol from stock_list where ts_code = #{ts_code}")
    String getSymbolByCode(String ts_code);



}
