package com.search.dto;

import lombok.Data;

import java.util.List;

@Data
public class CompanyInfoListMessage extends Message{
    List<CompanyInfo> companyInfoList;

}
