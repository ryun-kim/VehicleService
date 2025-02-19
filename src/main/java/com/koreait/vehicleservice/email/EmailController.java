package com.koreait.vehicleservice.email;

import javax.servlet.http.HttpServletRequest;

import com.koreait.vehicleservice.user.UserController;
import com.koreait.vehicleservice.user.UserEntity;
import com.koreait.vehicleservice.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/service")
public class EmailController {
    @Autowired EmailService emailService;
    @Autowired UserService service;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/mail/{idEmailInput}") //이메일 인증 코드 보내기
    @ResponseBody
    public void emailConfirm(@PathVariable String idEmailInput)throws Exception{
        logger.info("post emailConfirm");
        System.out.println("전달 받은 이메일 : "+idEmailInput);
        emailService.sendSimpleMessage(idEmailInput);
    }

    @PostMapping("/verifyCode/{code}/{email}") //이메일 안중코드 검증
    @ResponseBody
    public Map<String, String> verifyCode(@PathVariable String code, @PathVariable String email) {
        Map<String, String> res = new HashMap<>();
        logger.info("Post verifyCode");
        String result = "0";
        System.out.println("code : "+code);
        System.out.println("code match : "+ EmailServiceImpl.ePw.equals(code));
        if(EmailServiceImpl.ePw.equals(code)) {
            UserEntity dbUser = service.getId(email);
            res.put("id", dbUser.getUid());
            result = "1";
        }
        res.put("result", result);
        return res;
    }
}
