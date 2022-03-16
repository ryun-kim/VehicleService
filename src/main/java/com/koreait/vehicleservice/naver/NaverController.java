package com.koreait.vehicleservice.naver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
public class NaverController {

    @Autowired NaverService naverService;

    @GetMapping("/naver/request")
    public String getAuthorizationUrl(HttpSession session){
        return naverService.getAuthorizationUrl(session);
    }

    @GetMapping("/naver/callback")
    public String naverCallback(String code, HttpSession session){
        naverService.naverLogin(code, session);
        // return "네이버 인증 완료 코드값 : " + code; 쿼리스트링의 값은 파라미터로 받을수 있다.
        return "redirect:/home";
    }
}
