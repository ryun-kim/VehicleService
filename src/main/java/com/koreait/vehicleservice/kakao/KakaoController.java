package com.koreait.vehicleservice.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
@RequestMapping("/kakao")
public class KakaoController {

    @Autowired KaKaoService service;

    @GetMapping("/oauth")
    public void  kakaoCallback(@RequestParam String code) throws Exception {

        String access_Token = service.getKaKaoAccessToken(code);
        service.createKakaoUser(access_Token);

    }
}
