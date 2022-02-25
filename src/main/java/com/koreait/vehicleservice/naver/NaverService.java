package com.koreait.vehicleservice.naver;

import com.koreait.vehicleservice.user.UserEntity;
import com.koreait.vehicleservice.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NaverService {

    @Autowired UserMapper userMapper;

    public void navJoin(UserEntity navUser){
        userMapper.navInsUser(navUser);
    }

    public UserEntity navUserFind(String id){
        UserEntity entity = new UserEntity();
        entity.setUid(id);
        return userMapper.selUser(entity);
    }
}
