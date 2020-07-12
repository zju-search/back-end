package com.search.mapper;

import com.search.dto.StockDetailInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StockDetailInfoMapper {

    @Select("select name from stock_list where ts_code = #{ts_code}")
    String getNameByCode(String ts_code);

    @Select("select symbol from stock_list where ts_code = #{ts_code}")
    String getSymbolByCode(String ts_code);

    @Select("select open,high,low,close,vol,amount from new_daily where ts_code = #{ts_code}")
    StockDetailInfo getNewDailyInfos(String ts_code);


    @Select("select ${parameter} from new_daily_basic where ts_code = #{ts_code}")
    double getNewDailyBasicInfo(String ts_code,String parameter);

    @Select("select introduction from stock_company where ts_code = #{ts_code}")
    String getIntroduction(String ts_code);

    @Select("select main_business from stock_company where ts_code = #{ts_code}")
    String getMainBusiness(String ts_code);

}
