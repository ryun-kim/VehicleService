package com.koreait.vehicleservice.myPage;

import com.koreait.vehicleservice.MyUserUtils;
import com.koreait.vehicleservice.vehicle.VehicleDto;
import com.koreait.vehicleservice.vehicle.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class myPageService {
    @Autowired
    private myPageMapper mapper;

    @Autowired
    private MyUserUtils myUserUtils;

    public List<VehicleVo> likesList(VehicleDto dto){
        dto.setIuser(myUserUtils.getLoginUserPk());
        return mapper.likesList(dto);
    }

    public int delAllLike(VehicleDto dto){
        dto.setLikesiuser(myUserUtils.getLoginUserPk());
        return mapper.delAllLike(dto);
    }
}
