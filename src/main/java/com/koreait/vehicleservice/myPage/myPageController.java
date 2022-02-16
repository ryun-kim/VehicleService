package com.koreait.vehicleservice.myPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class myPageController {

    @GetMapping("/chat")
    public void chat(){}

    @GetMapping("/like")
    public void like(){}

    @GetMapping("/userinfo")
    public void userinfo(){}
}
