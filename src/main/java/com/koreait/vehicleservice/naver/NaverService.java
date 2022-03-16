package com.koreait.vehicleservice.naver;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class NaverService {

    @Autowired UserMapper userMapper;
    @Value("${cos.key}") private String cosKey;
    @Autowired MyUserUtils userUtils;

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

    public void naverLogin(String code, HttpSession session){
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

        //Gson, Json Simple, ObjectMapper
        //accesstoken을 자바객체로 가져오는 작업
        ObjectMapper obMapper = new ObjectMapper();
        NaverOauthToken oauthToken = null;
        try {
            oauthToken = obMapper.readValue(response.getBody(), NaverOauthToken.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        System.out.println("네이버 엑세스 토큰 : " + oauthToken.getAccess_token());

        // ------------------ 엑세스 토큰으로 네이버로그인사용자 정보 요청 ----------------------
        RestTemplate rt2 = new RestTemplate(); //http요청을 편하게 할 수 있다.

        //HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        //httpbody데이터가 key-value형태타입의 데이터라고 알려준다.
        headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        //body와 header부분을 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> naverProfileRequest = new HttpEntity<>(headers2);

        //http 요청하기 - post방식으로 - 그리고 response 변수의 응답받음
        ResponseEntity<String> response2 = rt2.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.POST,
                naverProfileRequest,
                String.class
        );

        //요청받는 네이버사용자정보를 자바객체로 변환
        ObjectMapper obMapper2 = new ObjectMapper();
        NaverProfile naverProfile = null;
        try {
            naverProfile = obMapper2.readValue(response2.getBody(), NaverProfile.class);
        } catch (JsonMappingException e){
            e.printStackTrace();
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }

        // UUID tempPassword = UUID.randomUUID(); 임시 비밀번호 생성(uuid란 중복되지않는 어떤 특정 값을 만들어내는 알고리즘)
        System.out.println("네이버 아이디(번호) : " + naverProfile.getResponse().getId());
        System.out.println("네이버 비번(임시비밀번호) : " + cosKey);
        System.out.println("네이버 닉네임 : " + naverProfile.getResponse().getName());
        System.out.println("네이버 생일 : " + naverProfile.getResponse().getBirthyear() + naverProfile.getResponse().getBirthday());
        System.out.println("네이버 폰번호 : " + naverProfile.getResponse().getMobile());
        System.out.println("네이버 이메일 : " + naverProfile.getResponse().getEmail());
        System.out.println("플랫폼 : " + "네이버");

        // ---------------------- 로그인 및 회원가입 처리 ---------------------------
        //toString() : Null PointerException(NPE)을 발생
        //String.valueOf() :  "null"이라는 문자열로 처리
        //그래서 NPE(Null PointerException)를 방지해야하는 경우에서는 String.valueOf()를 사용하는 것이 좋다.
        UserEntity navUser = new UserEntity();
        navUser.setUid(String.valueOf(naverProfile.getResponse().getId()));
        navUser.setUpw(BCrypt.hashpw(cosKey, BCrypt.gensalt()));
        navUser.setNm(naverProfile.getResponse().getName());
        navUser.setBirthday(naverProfile.getResponse().getBirthyear() + naverProfile.getResponse().getBirthday());
        navUser.setEmail(naverProfile.getResponse().getEmail()+"(네이버)");
        navUser.setPhnum(naverProfile.getResponse().getMobile());
        navUser.setPlatform("네이버");

        //가입자 혹은 비가입자 체크해서 처리
        UserEntity dbnavUser = navUserFind(String.valueOf(naverProfile.getResponse().getId()));
        if(dbnavUser == null){
            System.out.println("기존회원이 아니기에 자동 회원가입 진행");
            navJoin(navUser);
            UserEntity dbnavUser2 = navUserFind(String.valueOf(naverProfile.getResponse().getId()));
            navUser.setIuser(dbnavUser2.getIuser());
        } else {
            System.out.println("기존회원이기에 바로 로그인 진행");
            navUser.setIuser(dbnavUser.getIuser());
        }

        //로그인 처리
        userUtils.setLoginUser(navUser);
        System.out.println("네이버 로그인 성공");
    }

    public void navJoin(UserEntity navUser){
        userMapper.navInsUser(navUser);
    }

    public UserEntity navUserFind(String id){
        UserEntity entity = new UserEntity();
        entity.setUid(id);
        return userMapper.selUser(entity);
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
