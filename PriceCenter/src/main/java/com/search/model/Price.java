package com.search.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Price implements Serializable {
    String dtime;
    double price;
    private static final long serialVersionUID = 1L;
}
