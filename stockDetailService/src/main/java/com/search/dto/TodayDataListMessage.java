package com.search.dto;

import lombok.Data;

import java.util.List;
@Data
public class TodayDataListMessage extends Message{
    List<TodayData> todayDataList;
}
