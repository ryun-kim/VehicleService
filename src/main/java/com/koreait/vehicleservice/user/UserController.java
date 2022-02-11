package com.koreait.vehicleservice.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/login")
    public void login() {}

    @GetMapping("/find")
    public void find() {}

    @GetMapping("/join")
    public void join() {}
}
