package com.koreait.vehicleservice.vehicle;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehicleMapper {
    int inVehicle(VehicleEntity entity);
    int inCarimg(CarImageEntity entity);
    int selMaxiboard();
    List<VehicleVo> vehicleList(VehicleDto dto);
}
