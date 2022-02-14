package com.koreait.vehicleservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired UserService service;

    @GetMapping("/login")
    public void login() {}

    @GetMapping("/find")
    public void find() {}

    @GetMapping("/join") //th:filed에서 에러가났는데 @modelattribue추가해서 해결(값을 담을수있는 통을보내준다고 생각)
    public void getJoin(@ModelAttribute UserEntity userEntity) {}

    @PostMapping("/join")
    public String Postjoin(UserEntity userEntity){
        int rs = service.joinProc(userEntity);
        System.out.println(rs);
        return null;
    }
}
