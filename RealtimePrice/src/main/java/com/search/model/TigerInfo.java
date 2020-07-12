package com.search.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class TigerInfo implements Serializable {

    private String ts_code;

    private String name;

    private static final long serialVersionUID = 1L;
}
