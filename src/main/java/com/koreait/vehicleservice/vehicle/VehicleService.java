package com.koreait.vehicleservice.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
<<<<<<< HEAD
=======
import org.springframework.web.multipart.MultipartFile;
>>>>>>> 8c3d634b58c777243b6f702860f9f57c2d871ab0

@Service
public class VehicleService {
    @Autowired
    private VehicleMapper mapper;
<<<<<<< HEAD
}
=======
    public int inVehicle(VehicleDto dto){
        VehicleEntity entity = dto;
        return mapper.inVehicle(entity);
    }

//    public String uploadMainImg(MultipartFile mf){
//        if(mf==null){return null;}
//
//
//
//        final String PATH = "D:/upload/images/vehicle/"+userUtils.getLoginUserPk();
//        String fileNm = fileUtils.saveFile(PATH,mf);
//        System.out.println("fileNm :"+fileNm);
//        if(fileNm == null){return null;}
//
//        UserEntity entity = new UserEntity();
//        entity.setIuser(loginUser.getIuser());
//
//
//        //기존 파일명
//        String oldFilePath = PATH+"/"+loginUser.getProfileimg();
//        fileUtils.delFile(oldFilePath);
//
//        //파일명을 t_user 테이블에 업데이트
//        entity.setProfileimg(fileNm);
//        mapper.updUser(entity);
//
//        //세선 프로필 파일명을 수정해준다.
//        loginUser.setProfileimg(fileNm);
//        return fileNm;
//    }
}
>>>>>>> 8c3d634b58c777243b6f702860f9f57c2d871ab0
