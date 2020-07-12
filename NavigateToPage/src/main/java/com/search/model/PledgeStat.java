package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * pledge_stat
 * @author 
 */
@Data
public class PledgeStat implements Serializable {
    private Long index;

    private String tsCode;

    private String endDate;

    private Long pledgeCount;

    private Double unrestPledge;

    private Double restPledge;

    private Double totalShare;

    private Double pledgeRatio;

    private static final long serialVersionUID = 1L;
}