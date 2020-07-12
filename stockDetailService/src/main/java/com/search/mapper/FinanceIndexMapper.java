package com.search.mapper;


import com.search.dto.FinanceIndices;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FinanceIndexMapper {
    @Select("select ts_code from stock_list where symbol = #{symbol}")
    String getCodeBySymbol(String symbol);

    @Select("select ifnull(${parameter},0) from annual_last_fina " +
            "where ts_code = #{ts_code} and ann_date like CONCAT(#{year},'%')" +
            "limit 1")
    double getFinanceIndexByCode(String ts_code,String parameter,String year);

    @Select("select profit_to_op,or_yoy,profit_to_gr,netprofit_yoy,profit_dedt," +
            "eps,invest_capital,capital_rese_ps,undist_profit_ps,ocfps," +
            "roe,roe_yoy,roa,grossprofit_margin,netprofit_margin," +
            "debt_to_assets,current_ratio,quick_ratio,assets_to_eqt,debt_to_eqt," +
            "eqt_yoy,ocf_yoy,turn_days," +
            "assets_turn,ar_turn,ca_turn,fa_turn "+
            "from annual_last_fina " +
            "where ts_code = #{ts_code}")
    FinanceIndices[] getFinanceIndicesByCode(String ts_code);


    /*
    @Select("select ifnull(${parameter},0) from annual_last_fina " +
            "where ts_code = #{ts_code}")
    double[] getFinanceIndicesByCode(String ts_code,String parameter);
*/

}
