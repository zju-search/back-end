package com.search.dto;


import lombok.Data;

//筛选时传过来的条件对象
@Data
public class Condition {
    String type;
    int year;
    double minValue;
    double maxValue;
}
