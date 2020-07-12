package com.search.dto;

import lombok.Data;

@Data
public class CompanyInfo {
    String title;
    String info;

    public CompanyInfo(String title, String info){
        this.title = title;
        this.info = info;
    }
}
