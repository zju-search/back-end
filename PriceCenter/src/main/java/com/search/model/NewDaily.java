package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * new_daily
 * @author 
 */
@Data
public class NewDaily implements Serializable {
    private String tsCode;

    private String tradeDate;

    private String open;

    private String high;

    private String low;

    private String close;

    private String preClose;

    private String change;

    private String pctChg;

    private String vol;

    private String amount;

    private static final long serialVersionUID = 1L;
}