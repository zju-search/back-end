package com.search.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Realtime implements Serializable {
    private String tsCode;

    private String time;

    private String value;

    private static final long serialVersionUID = 1L;
}