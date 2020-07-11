package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * top10_holders
 * @author 
 */
@Data
public class TopHolder implements Serializable {
    private String index;

    private String tsCode;

    private String annDate;

    private String endDate;

    private String holderName;

    private String holdAmount;

    private String holdRatio;

    private static final long serialVersionUID = 1L;
}