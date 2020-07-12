package com.search.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class PledgeStatInfo implements Serializable {

    private String tsCode;

    private String endDate;

    private Long pledgeCount;

    private Double unrestPledge;

    private Double restPledge;

    private Double totalShare;

    private Double pledgeRatio;

    private static final long serialVersionUID = 1L;
}