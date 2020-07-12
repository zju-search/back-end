package com.search.dto;

import lombok.Data;

import java.util.List;

@Data
public class RankedObjectListMessage extends Message{
    List<RankedObject> rankedObjectList;
}
