package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * express
 * @author 
 */
@Data
public class Express implements Serializable {
    private Long index;

    private String tsCode;

    private String annDate;

    private String endDate;

    private Double revenue;

    private Double operateProfit;

    private Double totalProfit;

    private Double nIncome;

    private Double totalAssets;

    private Double totalHldrEqyExcMinInt;

    private Double dilutedEps;

    private Double dilutedRoe;

    private Double yoyNetProfit;

    private String bps;

    private String perfSummary;

    private static final long serialVersionUID = 1L;
}