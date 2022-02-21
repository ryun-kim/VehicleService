package com.koreait.vehicleservice.vehicle;

import com.koreait.vehicleservice.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleMapper mapper;

    @Autowired
    private MyFileUtils fileUtils;

    public int inVehicle(VehicleDto dto){
        VehicleEntity entity = dto;
        return mapper.inVehicle(entity);
    }

    public String uploadMainImg(MultipartFile mf){
        if(mf==null){return null;}
        var iboard = mapper.selMaxiboard();


        final String PATH = "../../resources/static/img/vehicle/"+iboard;
        fileUtils.delFolderFiles(PATH,true);
        String fileNm = fileUtils.saveFile(PATH,mf);
        System.out.println("fileNm :"+fileNm);
        if(fileNm == null){return null;}

        CarImageEntity entity = new CarImageEntity();


        //파일명을 db 에 저장하기
        entity.setSelliboard(iboard);
        entity.setMainimg(fileNm);
        mapper.inCarimg(entity);
//
//        //세선 프로필 파일명을 수정해준다.
//        loginUser.setProfileimg(fileNm);

        return fileNm;
    }

    public List<VehicleVo> vehicleList(VehicleDto dto){

        final String PATH = "../img/vehicle/";
        List<VehicleVo> list = mapper.vehicleList(dto);
        for(int i=0; i<list.size(); i++){
            String result =PATH + list.get(i).getSelliboard()+ "/"+list.get(i).getMainimg();
            list.get(i).setMainimg(result);
        }
        return list;
    }
}

//        return fileNm;
//    }

