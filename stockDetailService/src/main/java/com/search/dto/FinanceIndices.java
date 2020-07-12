package com.search.dto;

import lombok.Data;

@Data
public class FinanceIndices {
    double profit_to_op,or_yoy,profit_to_gr,netprofit_yoy,profit_dedt,
            eps,invest_capital,capital_rese_ps,undist_profit_ps,ocfps,
            roe,roe_yoy,roa,grossprofit_margin,netprofit_margin,
            debt_to_assets,current_ratio,quick_ratio,assets_to_eqt,debt_to_eqt,
            eqt_yoy,ocf_yoy,turn_days,
            assets_turn,ar_turn,ca_turn,fa_turn;
}
