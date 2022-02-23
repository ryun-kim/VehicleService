package com.koreait.vehicleservice.naver;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import javax.servlet.http.HttpSession;

@Controller
public class NaverController {


    @GetMapping("/naver/request")
    public String getAuthorizationUrl(HttpSession session){
        /* 세션 유효성 검증을 위하여 난수를 생성 */
        String state = UUID.randomUUID().toString();

        /* 생성한 난수 값을 session에 저장 */
        setSession(session, state);

        /* 생성한 난수 값을 가져오기 */
        String sts = getSession(session);

        return "redirect:https://nid.naver.com/oauth2.0/authorize?response_type=code" +
                "&client_id=FKI5X4pkLPGeuMSpAt6W&redirect_url=http%3a%2f%2flocalhost%3a8090%2fnaver%2fcallback" +
                "&state="+sts;
    }

    @GetMapping("/naver/callback")
    public @ResponseBody String naverCallback(String code, HttpSession session){
        // ------------------ 네이버 로그인 액세스 토큰 받기 --------------------
        //POST방식으로 key=value 데이터를 요청 (네이버쪽으로)
        RestTemplate rt = new RestTemplate(); //http요청을 편하게 할 수 있다.

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();

        //httpbody데이터가 key-value형태타입의 데이터라고 알려준다.
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "FKI5X4pkLPGeuMSpAt6W");
        params.add("client_secret", "WFVpNVrbz0");
        params.add("code", code);
        params.add("state", getSession(session));

        //body와 header부분을 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> naverTokenRequest = new HttpEntity<>(params, headers);

        //http 요청하기 - post방식으로 - 그리고 response 변수의 응답받음
        ResponseEntity<String> response = rt.exchange(
                "https://nid.naver.com/oauth2.0/token",
                HttpMethod.POST,
                naverTokenRequest,
                String.class
        );

        return response.getBody();
    }

    /* http session에 데이터 저장 */
    private void setSession(HttpSession session,String state){
        session.setAttribute("state", state);
    }
    /* http session에서 데이터 가져오기 */
    private String getSession(HttpSession session){
        return (String) session.getAttribute("state");
    }
}
