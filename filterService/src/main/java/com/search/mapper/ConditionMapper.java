package com.search.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConditionMapper {
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
}
