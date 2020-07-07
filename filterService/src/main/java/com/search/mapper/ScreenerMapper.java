package com.search.mapper;


import com.search.dto.MinMaxValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScreenerMapper {
    @Select("select max(trade_date) from daily")
    String getLatestTradeDate();

    @Select("select ts_code from ${tableName}" +
            "where trade_date like CONCAT(#{year},'%')" +
            "and min(${type}) >= #{minValue} and max(${type}) <= #{maxValue}" +
            "group by ts_code")
    List<String> getSuitableCodesInTradeYear(String type,String year,
                                             double minValue,double maxValue,
                                             String tableName);

    @Select("select ts_code from ${tableName}" +
            "where ann_date like CONCAT(#{year},'%')" +
            "and min(${type}) >= #{minValue} and max(${type}) <= #{maxValue}" +
            "group by ts_code")
    List<String> getSuitableCodesInAnnYear(String type,String year,
                                             double minValue,double maxValue,
                                             String tableName);

    @Select("select min(${type}) as minValue,max(${type}) as maxValue from ${tableName}" +
            "where trade_date like CONCAT(#{year},'%')" +
            "group by ts_code")
    MinMaxValue getValueInTradeYear(String type,String ts_code,String year,String tableName);

    @Select("select min(${type}) as minValue,max(${type}) as maxValue from ${tableName}" +
            "where ann_date like CONCAT(#{year},'%')" +
            "group by ts_code")
    MinMaxValue getValueInAnnYear(String type,String ts_code,String year,String tableName);

    @Select("select symbol from stock_list where ts_code = #{ts_code}")
    String getSymbolByCode(String ts_code);

    @Select("select name from stock_list where ts_code = #{ts_code}")
    String getNameByCode(String ts_code);

    @Select("select area from stock_list where ts_code = #{ts_code}")
    String getAreaByCode(String ts_code);

    @Select("select industry from stock_list where ts_code = #{ts_code}")
    String getIndustryByCode(String ts_code);

    @Select("select close from daily where ts_code = #{ts_code} and trade_date = #{latestDate}")
    String getCloseByCode(String ts_code,String latestDate);

    @Select("select pct_chg from daily where ts_code = #{ts_code} and trade_date = #{latestDate}")
    String getPctChgByCode(String ts_code,String latestDate);

}
