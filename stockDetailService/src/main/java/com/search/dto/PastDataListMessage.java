package com.search.dto;

import lombok.Data;

import java.util.List;

@Data
public class PastDataListMessage extends Message{
    List<PastData> pastDataList;
}
