package com.koreait.vehicleservice.user;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserEntity userEntity);
    UserEntity selUser(UserEntity entity);
}
