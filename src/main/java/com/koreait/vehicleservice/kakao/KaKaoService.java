package com.koreait.vehicleservice.kakao;

import com.koreait.vehicleservice.user.UserEntity;
import com.koreait.vehicleservice.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class KaKaoService {

    @Autowired UserMapper userMapper;

    public void kaoJoin(UserEntity kaoUser){
        userMapper.kaoInsUser(kaoUser);
    }

    public UserEntity kaoUserFind(String id){
        UserEntity entity = new UserEntity();
        entity.setUid(id);
        return userMapper.selUser(entity);
    }
}
