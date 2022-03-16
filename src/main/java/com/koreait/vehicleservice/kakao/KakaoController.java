package com.koreait.vehicleservice.kakao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KakaoController {

    @Autowired KaKaoService kaKaoService;

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code){
        kaKaoService.kakaoLogin(code);
        // return "카카오 인증 완료 코드값 : " + code; 쿼리스트링의 값은 파라미터로 받을수 있다.
        return "redirect:/home";
    }
}
