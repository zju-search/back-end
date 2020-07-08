package com.search.service;

import com.alibaba.fastjson.JSONObject;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    JSONObject Register(String email, String password, String username) throws NoSuchAlgorithmException;

    JSONObject Login(String email, String password) throws NoSuchAlgorithmException;

    JSONObject modifyPwd(String email, String password) throws NoSuchAlgorithmException;

    JSONObject getCaptcha(String email);
}

