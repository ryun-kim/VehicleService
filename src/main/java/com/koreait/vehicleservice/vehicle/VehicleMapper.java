package com.koreait.vehicleservice.vehicle;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehicleMapper {
    int inVehicle(VehicleEntity entity);
    int inCarimg(CarImageEntity entity);
    int selMaxiboard();
    int inOptions(CarOption car_option);
    int inExplanation(CarExplanationEntity car_explanation);
    VehicleEntity selCarNum(VehicleEntity entity);
    List<VehicleVo> vehicleList(VehicleEntity entity);
    VehicleVo vehicledetail(VehicleEntity entity);
    int likes(VehicleDto dto);
    int dellikes(VehicleDto dto);
    int jimchk(VehicleDto dto);
    VehicleDto selMaxPageVal(VehicleDto dto);
}