package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * daily
 * @author zcc
 */
@Data
public class Daily implements Serializable {
    private Long index;

    private String tsCode;

    private String tradeDate;

    private Double open;

    private Double high;

    private Double low;

    private Double close;

    private Double preClose;

    private Double change;

    private Double pctChg;

    private Double vol;

    private Double amount;

    private static final long serialVersionUID = 1L;
}