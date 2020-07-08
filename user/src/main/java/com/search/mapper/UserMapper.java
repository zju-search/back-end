package com.search.mapper;

import com.search.model.User;

public interface UserMapper {
    User selectByName(String name);

    User selectByEmail(String email);

    int updateByEmail(User record);

    int insert(User record);

    int insertSelective(User record);
}