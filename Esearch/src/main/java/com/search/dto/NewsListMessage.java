package com.search.dto;

import lombok.Data;

import java.util.List;

@Data
public class NewsListMessage extends Message{
    List<News> newsList;
}
