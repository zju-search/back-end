package com.search.model;

import lombok.Data;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Data
public class Encryption {
    private String plainText;//明文
    private String cipherText;//密文

    public String encrypt(String s)throws NoSuchAlgorithmException {

        MessageDigest md= MessageDigest.getInstance("SHA");
        md.update(s.getBytes());
        String str=new BigInteger(md.digest()).toString(32);
        return str;

    }
}