package com.search.dto;

import lombok.Data;

@Data
public class ConditionMessage extends Message{
    double minValue;
    double maxValue;
}
