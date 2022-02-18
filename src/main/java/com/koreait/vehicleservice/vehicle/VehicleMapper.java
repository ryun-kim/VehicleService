package com.koreait.vehicleservice.vehicle;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VehicleMapper {
    int inVehicle(VehicleEntity entity);
}
