package com.koreait.vehicleservice.naver;

import lombok.Data;

@Data //getter, setter
public class NaverOauthToken {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
}
