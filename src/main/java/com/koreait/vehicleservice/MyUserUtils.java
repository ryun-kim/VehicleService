package com.koreait.vehicleservice;

import com.koreait.vehicleservice.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class MyUserUtils {

    @Autowired private HttpSession hs;

    public void setLoginUser(UserEntity entity){
        hs.setAttribute("loginUser", entity);
    }

    public UserEntity getLoginUser(){
        return (UserEntity) hs.getAttribute("loginUser");
    }

    public int getLoginUserPk(){
        return getLoginUser() == null ? 0 : getLoginUser().getIuser();
    }
}
