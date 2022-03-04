package com.koreait.vehicleservice.myPage;

import com.koreait.vehicleservice.vehicle.VehicleDto;
import com.koreait.vehicleservice.vehicle.VehicleVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface myPageMapper {
    List<VehicleVo> likesList(VehicleDto dto);
    int delAllLike (VehicleDto dto);

}
