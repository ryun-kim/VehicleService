package com.koreait.vehicleservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

@Service
public class UserService {

    @Autowired UserMapper mapper;

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
}
