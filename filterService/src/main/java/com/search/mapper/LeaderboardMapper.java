package com.search.mapper;


import com.search.dto.RankedObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeaderboardMapper {
    @Select("select ts_code,${parameter} as value from ${tableName} " +
            "order by ${parameter} desc limit 20")
    List<RankedObject> getTopTwentyStockOnNotFina(String parameter,String tableName);

    @Select("select ts_code,${parameter} as value from new_fina_indicator" +
            " order by ${parameter} desc limit 20")
    List<RankedObject> getTopTwentyStockOnFina(String parameter);

    @Select("select ts_code,${parameter} as value from ${tableName} " +
            "order by ${parameter} asc limit 20")
    List<RankedObject> getBottomTwentyStockOnNotFina(String parameter,String tableName);

    @Select("select ts_code,${parameter} as value from new_fina_indicator" +
            " order by ${parameter} asc limit 20")
    List<RankedObject> getBottomTwentyStockOnFina(String parameter);

    @Select("select ifnull(close,0) from new_daily where ts_code = #{ts_code}")
    String getCloseByCode(String ts_code);

    @Select("select name from stock_list where ts_code = #{ts_code}")
    String getNameByCode(String ts_code);

    @Select("select symbol from stock_list where ts_code = #{ts_code}")
    String getSymbolByCode(String ts_code);

}
