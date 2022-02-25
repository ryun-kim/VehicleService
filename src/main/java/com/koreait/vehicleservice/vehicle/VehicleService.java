package com.koreait.vehicleservice.vehicle;

import com.koreait.vehicleservice.MyFileUtils;
import com.koreait.vehicleservice.MyUserUtils;
import com.koreait.vehicleservice.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleMapper mapper;
    @Autowired
    private MyFileUtils fileUtils;
    @Autowired
    private MyUserUtils myUserUtils;

    public int inVehicle(VehicleEntity entity){ //차량등록
        return mapper.inVehicle(entity);
    }


    public String uploadMainImg(MultipartFile mf){  //메인 이미지 저장
        if(mf==null){return null;}
        var iboard = mapper.selMaxiboard();

        final String PATH = "D:/upload/images/vehicle/"+iboard;
        fileUtils.delFolderFiles(PATH,true);
        String fileNm = fileUtils.saveFile(PATH,mf);
        if(fileNm == null){return null;}

        CarImageEntity entity = new CarImageEntity();

        //파일명을 db 에 저장하기
        entity.setSelliboard(iboard);
        entity.setMainimg(fileNm);
        mapper.inCarimg(entity);

        return fileNm;
    }

    public String uploadSubImg(List<MultipartFile> mhsr){ //서브이미지 저장
        var iboard = mapper.selMaxiboard();
        final String PATH = "D:/upload/images/vehicle/"+iboard+"/sub";
        fileUtils.delFolderFiles(PATH,true);
        for (MultipartFile mf : mhsr) {
            if(mf==null){return null;}
            String fileNm = fileUtils.saveFile(PATH,mf);
            if(fileNm == null){return null;}
        }
        return "";
    }

    public int inOptions(VehicleDto dto){
        String[] options = dto.getChecked_option();
        CarOption car_option = new CarOption();
        var iboard = mapper.selMaxiboard();
        car_option.setSelliboard(iboard);
        for(int i=0; i<options.length; i++){
            switch (options[i]){
                case "aircon" :car_option.setAircon(1);
                    break;
                case "smart_key":car_option.setSmart_key(1);
                    break;
                case "camera":car_option.setCamera(1);
                    break;
                case "hi_pass":car_option.setHi_pass(1);
                    break;
                case "navigation":car_option.setNavigation(1);
                    break;
                case "bluetooth":car_option.setBluetooth(1);
                    break;
            }
        }
        return mapper.inOptions(car_option);
    }

    public int inExplanation(VehicleDto dto){
        String[] explanation = dto.getExplanations();
        CarExplanationEntity car_explanation = new CarExplanationEntity();
        var iboard = mapper.selMaxiboard();
        car_explanation.setSelliboard(iboard);
        car_explanation.setCar_state(explanation[0]);
        car_explanation.setAccident_state(explanation[1]);
        car_explanation.setManage_state(explanation[2]);
        car_explanation.setInout_inform(explanation[3]);
        car_explanation.setTuning_inform(explanation[4]);
        car_explanation.setOther(explanation[5]);

        return mapper.inExplanation(car_explanation);
    }

    public int selCarNum(String car_num){
        VehicleEntity entity = new VehicleEntity();
        entity.setCar_number(car_num);
        String CarNumber = mapper.selCarNum(entity).getCar_number();
        System.out.println(CarNumber);
        if(CarNumber.equals(car_num)){
            return 1; //중복
        }
        return 0; //사용가능 차량번호
    }


    public List<VehicleVo> vehicleList(VehicleDto dto){
        int startIdx = (dto.getCurrentPage() - 1) * dto.getRecordCount();
        if(startIdx < 0) { startIdx = 0; }
        dto.setStartIdx(startIdx);
        return mapper.vehicleList(dto);
    }

    public VehicleVo vehicledetail(VehicleEntity entity){
        VehicleVo vo= mapper.vehicledetail(entity);
        int iboard = entity.getSelliboard();
        String strDirPath = "D:\\upload\\images\\vehicle\\"+iboard+"\\sub";
        List subimg = ListFile( strDirPath );
        vo.setSubimg(subimg);
        return vo;
    }


    private List<String> ListFile( String strDirPath ) {
        List list = new ArrayList<String>();
        File path = new File(strDirPath);
        File[] fList = path.listFiles();
        for (int i = 0; i < fList.length; i++) {
            if (fList[i].isFile()) {
                list.add(fList[i].getName());
            }
        }
        return list;
    }

        public List<VehicleVo> vehicleList4(VehicleEntity entity){

        final String PATH = "../img/vehicle/";
        List<VehicleVo> list = mapper.vehicleList(entity);
        for(int i=0; i<list.size(); i++){
            String result =PATH + list.get(i).getSelliboard()+ "/"+list.get(i).getMainimg();
            list.get(i).setMainimg(result);
        }
        return list;
    }

    public int likes(VehicleDto dto){
        dto.setLikesiuser(myUserUtils.getLoginUserPk());
        return mapper.likes(dto);
    }
    public int dellikes(VehicleDto dto){
        dto.setLikesiuser(myUserUtils.getLoginUserPk());
        return mapper.dellikes(dto);
    }

    public int jimchk(VehicleDto dto){
        dto.setLikesiuser(myUserUtils.getLoginUserPk());
        return mapper.jimchk(dto);
    }

    public VehicleDto selMaxPageVal(VehicleDto dto){
        return mapper.selMaxPageVal(dto);
    }
}

