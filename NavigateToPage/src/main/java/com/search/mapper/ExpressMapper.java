package com.search.mapper;

import com.search.model.Express;

public interface ExpressMapper {
    Express selectByTscode(String tscode);

    int insert(Express record);

    int insertSelective(Express record);
}