package com.koreait.vehicleservice.myPage;

import com.koreait.vehicleservice.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class myPageController {
    @Autowired
    private myPageService service;

    @GetMapping("/chat")
    public void chat(){}

    @GetMapping("/like")
    public void like(){}

    @GetMapping("/userinfo")
    public void userinfo(){}
}
