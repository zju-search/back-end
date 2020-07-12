package com.search.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CompanyMapper {
    @Select("select name from stock_list where symbol = #{symbol}")
    String getNameBySymbol(String symbol);

    @Select("select ts_code from stock_list where symbol = #{symbol}")
    String getCodeBySymbol(String symbol);

    @Select("select ${parameter} from stock_company where ts_code = #{ts_code}")
    String getCompanyInfoByCode(String ts_code,String parameter);

}
