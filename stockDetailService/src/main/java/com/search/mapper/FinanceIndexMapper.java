package com.search.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FinanceIndexMapper {
    @Select("select ts_code from stock_list where symbol = #{symbol}")
    String getCodeBySymbol(String symbol);

    @Select("select ifnull(${parameter},0) from fina_indicator " +
            "where ts_code = #{ts_code} and ann_date like CONCAT(#{year},'%')" +
            "limit 1")
    double getFinanceIndexByCode(String ts_code,String parameter,String year);

}
