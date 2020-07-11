package com.search.model;

import java.io.Serializable;
import lombok.Data;

/**
 * stk_holdernumber
 * @author 
 */
@Data
public class Holdernumber implements Serializable {
    private Long index;

    private String tsCode;

    private String annDate;

    private String endDate;

    private Long holderNum;

    private static final long serialVersionUID = 1L;
}