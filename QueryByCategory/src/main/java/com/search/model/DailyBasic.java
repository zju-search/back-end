package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * daily_basic
 * @author zcc
 */
@Data
public class DailyBasic implements Serializable {
    private Long index;

    private String tsCode;

    private String tradeDate;

    private Double close;

    private Double turnoverRate;

    private Double turnoverRateF;

    private Double volumeRatio;

    private Double pe;

    private Double peTtm;

    private Double pb;

    private Double ps;

    private Double psTtm;

    private Double dvRatio;

    private Double dvTtm;

    private Double totalShare;

    private Double floatShare;

    private Double freeShare;

    private Double totalMv;

    private Double circMv;

    private static final long serialVersionUID = 1L;
}