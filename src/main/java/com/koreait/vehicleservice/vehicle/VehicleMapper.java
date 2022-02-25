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
    List<VehicleVo> vehicleSearchList(VehicleDto dto);
    VehicleVo vehicledetail(VehicleEntity entity);
}