package com.search.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConditionMapper {
    /*
    @Select("select max(${parameter}) from ${tableName} " +
            "where trade_date like CONCAT(#{year},'%')")
    double getMaxValueInTradeYear(String parameter,String year,String tableName);

    @Select("select min(${parameter}) from ${tableName} " +
            "where trade_date like CONCAT(#{year},'%')")
    double getMinValueInTradeYear(String parameter,String year,String tableName);

    @Select("select max(${parameter}) from ${tableName} " +
            "where ann_date like CONCAT(#{year},'%')")
    double getMaxValueInAnnYear(String parameter,String year,String tableName);

    @Select("select min(${parameter}) from ${tableName} " +
            "where ann_date like CONCAT(#{year},'%')")
    double getMinValueInAnnYear(String parameter,String year,String tableName);

     */

    @Select("select ifnull(max(${parameter}),0) from year_fina where year = #{year}")
    double getMaxFinaValue(String parameter,String year);

    @Select("select ifnull(min(${parameter}),0) from year_fina where year = #{year}")
    double getMinFinaValue(String parameter,String year);

    @Select("select ifnull(max(${parameter}),0) from ${tableName}")
    double getMaxNotFinaValue(String parameter,String tableName);

    @Select("select ifnull(min(${parameter}),0) from ${tableName}")
    double getMinNotFinaValue(String parameter,String tableName);

}
