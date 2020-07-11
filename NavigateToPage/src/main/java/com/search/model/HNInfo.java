package com.search.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class HNInfo implements Serializable {

    private String ann_date;

    private String end_date;

    private Long holder_num;

    private static final long serialVersionUID = 1L;
}
