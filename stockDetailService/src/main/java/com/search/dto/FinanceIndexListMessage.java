package com.search.dto;

import lombok.Data;

import java.util.List;

@Data
public class FinanceIndexListMessage extends Message{

    List<FinanceIndex> financeIndexList;
}
