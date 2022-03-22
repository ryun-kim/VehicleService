package com.koreait.vehicleservice.kakao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.koreait.vehicleservice.MyUserUtils;
import com.koreait.vehicleservice.user.UserEntity;
import com.koreait.vehicleservice.user.UserMapper;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class KaKaoService {

    @Autowired UserMapper userMapper;
    @Value("${cos.key}") private String cosKey;
    @Autowired MyUserUtils userUtils;

    public void kakaoLogin(String code){
        // ------------------ 카카오 로그인 액세스 토큰 받기 --------------------
        //POST방식으로 key=value 데이터를 요청 (카카오쪽으로)
        RestTemplate rt = new RestTemplate(); //http요청을 편하게 할 수 있다.

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        //httpbody데이터가 key-value형태타입의 데이터라고 알려준다.
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "0e08b6c96d5f791a6eb59bd54c31aa90");
        params.add("redirect_uri", "http://3.39.60.222:8080/auth/kakao/callback");
        params.add("code", code);

        //body와 header부분을 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        //http 요청하기 - post방식으로 - 그리고 response 변수의 응답받음
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        //Gson, Json Simple, ObjectMapper
        //accesstoken을 자바객체로 가져오는 작업
        ObjectMapper obMapper = new ObjectMapper();
        KakaoOAuthToken oauthToken = null;
        try {
            oauthToken = obMapper.readValue(response.getBody(), KakaoOAuthToken.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());

        // ------------------ 엑세스 토큰으로 카카오로그인사용자 정보 요청 ----------------------
        RestTemplate rt2 = new RestTemplate(); //http요청을 편하게 할 수 있다.

        //HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        //httpbody데이터가 key-value형태타입의 데이터라고 알려준다.
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //body와 header부분을 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        //http 요청하기 - post방식으로 - 그리고 response 변수의 응답받음
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        //요청받는 카카오사용자정보를 자바객체로 변환
        ObjectMapper obMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = obMapper2.readValue(response2.getBody(), KakaoProfile.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        // UUID tempPassword = UUID.randomUUID(); 임시 비밀번호 생성(uuid란 중복되지않는 어떤 특정 값을 만들어내는 알고리즘)
        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 비번(임시비밀번호) : " + cosKey);
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        System.out.println("카카오 생일 : " + kakaoProfile.getKakao_account().getBirthday());
        System.out.println("카카오 닉네임 : " + kakaoProfile.getProperties().getNickname());

        // ---------------------- 로그인 및 회원가입 처리 ---------------------------
        //toString() : Null PointerException(NPE)을 발생
        //String.valueOf() :  "null"이라는 문자열로 처리
        //그래서 NPE(Null PointerException)를 방지해야하는 경우에서는 String.valueOf()를 사용하는 것이 좋다.
        UserEntity kaoUser = new UserEntity();
        kaoUser.setUid(String.valueOf(kakaoProfile.getId()));
        kaoUser.setUpw(BCrypt.hashpw(cosKey, BCrypt.gensalt()));
        kaoUser.setNm(kakaoProfile.getProperties().getNickname());
        kaoUser.setBirthday(kakaoProfile.getKakao_account().getBirthday());
        kaoUser.setEmail(kakaoProfile.getKakao_account().getEmail()+"(카카오)");
        kaoUser.setPlatform("카카오");

        //가입자 혹은 비가입자 체크해서 처리
        UserEntity dbKaoUser = kaoUserFind(String.valueOf(kakaoProfile.getId()));
        if(dbKaoUser == null){
            System.out.println("기존회원이 아니기에 자동 회원가입 진행");
            kaoJoin(kaoUser);
            UserEntity dbKaoUser2 = kaoUserFind(String.valueOf(kakaoProfile.getId()));
            kaoUser.setIuser(dbKaoUser2.getIuser());
        } else {
            System.out.println("기존회원이기에 바로 로그인 진행");
            kaoUser.setIuser(dbKaoUser.getIuser());
        }

        //로그인 처리
        userUtils.setLoginUser(kaoUser);
        System.out.println("카카오 로그인 성공");
    }

    public void kaoJoin(UserEntity kaoUser){
        userMapper.kaoInsUser(kaoUser);
    }

    public UserEntity kaoUserFind(String id){
        UserEntity entity = new UserEntity();
        entity.setUid(id);
        return userMapper.selUser(entity);
    }
}
