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

        int startIdx = (dto.getCurrentPage() - 1) * dto.getRecordCount();
        if(startIdx < 0) { startIdx = 0; }
        dto.setStartIdx(startIdx);
        dto.setIuser(myUserUtils.getLoginUserPk());
        return mapper.likesList(dto);
    }

    public int delAllLike(VehicleDto dto){
        dto.setLikesiuser(myUserUtils.getLoginUserPk());
        List<Integer> list = mapper.seldeliboard(dto);
        if(list.size()>0){
            for (int i =0; i<list.size();i++){
                VehicleDto delDto = new VehicleDto();
                delDto.setSelliboard(list.get(i));
                System.out.println("list.get(i)");
                System.out.println(list.get(i));
                mapper.delllikesum(delDto);
            }
        }
        return mapper.delAllLike(dto);
    }

    public VehicleDto selMaxPageVal(VehicleDto dto){
        return mapper.selMaxPageVal(dto);
    }

}
