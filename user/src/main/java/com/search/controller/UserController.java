package com.search.controller;

import com.alibaba.fastjson.JSONObject;
import com.search.mapper.UserMapper;
import com.search.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value = "/Register", method = RequestMethod.POST)
    public JSONObject Register(@RequestParam(name = "email") String email,
                                @RequestParam(name = "password") String password,
                                @RequestParam(name = "username") String username) throws NoSuchAlgorithmException {

        return userService.Register(email, password, username);
    }

    @RequestMapping(value = "/Login", method = RequestMethod.POST)
    public JSONObject Login(@RequestParam(name = "email") String email,
                                @RequestParam(name = "password") String password) throws NoSuchAlgorithmException {

        return userService.Login(email, password);
    }

    @RequestMapping(value = "/modifyPwd", method = RequestMethod.POST)
    public JSONObject modifyPwd(@RequestParam(name = "email") String email,
                            @RequestParam(name = "password") String password) throws NoSuchAlgorithmException {

        return userService.modifyPwd(email, password);
    }

    @RequestMapping(value = "/getCaptcha", method = RequestMethod.POST)
    public JSONObject getCaptcha(@RequestParam(name = "email") String email) throws NoSuchAlgorithmException {

        return userService.getCaptcha(email);
    }
}
