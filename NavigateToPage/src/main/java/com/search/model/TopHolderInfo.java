package com.search.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TopHolderInfo implements Serializable {
    private String ann_date;

    private String end_date;

    private String holder_name;

    private String hold_amount;

    private String hold_ratio;

    private String last_change;

    private static final long serialVersionUID = 1L;
}