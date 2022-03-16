package com.koreait.vehicleservice.user;

import com.koreait.vehicleservice.MyUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserService {

    @Autowired UserMapper mapper;
    @Autowired MyUserUtils userUtils;


    //회원가입
    public int joinProc(UserEntity userEntity){
        UserEntity entity = new UserEntity();
        userEntity.setBirthday(entity.combineBirthday(userEntity.getBirthdayYear(), userEntity.getBirthdayMonth(), userEntity.getBirthdayDay()));
        userEntity.setEmail(entity.combineEmail(userEntity.getFrontEmail(), userEntity.getBackEmail()));
        userEntity.setUpw(BCrypt.hashpw(userEntity.getUpw(), BCrypt.gensalt()));
        return mapper.insUser(userEntity);
    }

    //아이디가 없으면 리턴1, 있으면 리턴 0
    public int idChk(String uid){
        UserEntity entity = new UserEntity();
        entity.setUid(uid);

        UserEntity result = mapper.selUser(entity);

        if(result == null){
            return 1;
        } else {
            return 0;
        }
    }

    //로그인
    public int login(UserEntity entity){
        UserEntity loginUser = null;
        loginUser = mapper.selUser(entity);
        if(loginUser == null){
            return 2; //아이디 없음
        } else if(BCrypt.checkpw(entity.getUpw(), loginUser.getUpw())){
            loginUser.setUpw(null);
            userUtils.setLoginUser(loginUser);
            return 1; //로그인 성공
        }
        return 3; //비밀번호 틀림
    }

    //이메일을 이용하여 dbUser 체크
    public int emailChk(String email){
        UserEntity entity = new UserEntity();
        entity.setEmail(email);

        UserEntity dbUser = mapper.selUser(entity);

        if(dbUser == null){
            return 0; //회원정보없음
        } else {
            return 1; //회원정보있음
        }
    }

    //이메일, id를 이용하여 dbUser 체크
    public int emailIdChk(UserEntity entity){
        UserEntity dbUser = mapper.selUser(entity);
        if(dbUser != null){
            return 1; //회원정보있음
        }
        return 0; //회원정보없음
    }

    //id찾기
    public UserEntity getId(String email){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        return mapper.selUser(userEntity);
    }

    //비밀번호 변경
    public void pasChn(UserEntity userEntity){
        userEntity.setUpw(BCrypt.hashpw(userEntity.getUpw(), BCrypt.gensalt()));
        mapper.updUserUpw(userEntity);
    }

    //마이페이지 비밀번호 변경
    public int myPgPasChn(UserEntity userEntity){
        userEntity.setUid(userUtils.getLoginUser().getUid());
        UserEntity dbUser = mapper.selUser(userEntity);
        if(!BCrypt.checkpw(userEntity.getUpw(), dbUser.getUpw())){
            return 2; //현재비밀번호 다름
        }
        String hashedPw = BCrypt.hashpw(userEntity.getNewupw(), BCrypt.gensalt());
        userEntity.setUpw(hashedPw);
        return mapper.updUserUpw(userEntity); //비번 변경 성공
    }
}
