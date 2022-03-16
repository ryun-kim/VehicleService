package com.koreait.vehicleservice.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserEntity {
    private int iuser;
    private String uid;
    private String upw;
    private String newupw;
    private String nm;
    private String birthday;
    private String birthdayYear;
    private int birthdayMonth;
    private int birthdayDay;
    private String phnum;
    private String email;
    private String frontEmail;
    private String backEmail;
    private String platform;
    private String profileimg;
    private double averating;
    private String introduction;

    //생년월일 19960510 표현으로 조합
    public String combineBirthday(String birthdayYear, int birthdayMonth, int birthdayDay){
        return birthdayYear + String.format("%02d", birthdayMonth) +  String.format("%02d", birthdayDay);
    }

    //이메일 wodhks6307@naver.com 표현으로 조합
    public String combineEmail(String frontEmail, String backEmail){
        return frontEmail + "@" + backEmail;
    }
}
