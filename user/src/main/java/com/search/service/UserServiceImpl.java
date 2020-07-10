package com.search.service;

import com.alibaba.fastjson.JSONObject;
import com.search.mapper.UserMapper;
import com.search.model.Encryption;
import com.search.model.User;
import com.search.provider.RedisProvider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;
    @Resource
    EncryptService encryptService;
    @Resource
    RedisProvider redisProvider;
    @Resource
    MailService mailService;

    @Override
    public JSONObject Register(String email, String password, String username) throws NoSuchAlgorithmException {

        System.out.println(email);
        System.out.println(password);
        System.out.println(username);

        JSONObject res = new JSONObject();
        res.put("state", false);
        res.put("message", "服务器出错");
        res.put("token", null);

        if (userMapper.selectByName(username) != null) {
            res.put("message", "用户名重复");
            return res;
        }
        if (userMapper.selectByEmail(email) != null) {
            res.put("message", "该邮箱已被注册");
            return res;
        }

        User user = new User();
        Encryption encryption=new Encryption();
        String cipherText=encryption.encrypt(password);

        user.setPassword(cipherText);
        user.setEmail(email);
        user.setUsername(username);

        int b = userMapper.insert(user);

        if (b > 0) {
            res.put("state", true);
            res.put("message", "");
            String authorizeToken = encryptService.getMD5Code(email);
            res.put("token", authorizeToken);
            redisProvider.setAuthorizeToken(authorizeToken, email);
        }

        return res;
    }

    @Override
    public JSONObject Login(String email, String password) throws NoSuchAlgorithmException {
        JSONObject res = new JSONObject();
        res.put("state", false);
        res.put("message", null);
        res.put("token", null);
        res.put("username", null);

        User user = userMapper.selectByEmail(email);

        if (user == null) {
            res.put("message", "邮箱不存在");
            return res;
        }

        Encryption encryption=new Encryption();
        String cipherText=encryption.encrypt(password);
        if (!user.getPassword().equals(cipherText)) {
            res.put("message", "密码错误");
            return res;
        }

        String authorizeToken = encryptService.getMD5Code(email);
        res.put("token", authorizeToken);
        res.put("username", user.getUsername());
        res.put("state", true);
        redisProvider.setAuthorizeToken(authorizeToken, email);

        return res;
    }

    @Override
    public JSONObject modifyPwd(String email, String password) throws NoSuchAlgorithmException {
        JSONObject res = new JSONObject();

        User user = userMapper.selectByEmail(email);

        if (user == null ) {
            res.put("state", false);
            res.put("message", "邮箱错误");
            return res;
        }
        else {
            Encryption encryption=new Encryption();
            String cipherText=encryption.encrypt(password);
            user.setPassword(cipherText);
            userMapper.updateByEmail(user);
        }

        res.put("state", true);
        res.put("message", "");

        return res;
    }

    @Override
    public JSONObject getCaptcha(String email) {
        JSONObject res = new JSONObject();

        int captcha = (int) ((Math.random() * 0.9 + 0.1)*10000);

        mailService.sendSimpleMail(email, "韭菜之家-邮箱验证码", "请确认您的验证码：" + captcha);

        res.put("state", true);
        res.put("message", "邮箱已发送");
        res.put("captcha", captcha);

        return res;
    }
}
