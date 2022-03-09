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
    List<VehicleVo> homeSearchList(VehicleEntity entity);
    List<VehicleVo> vehicleSearchList(VehicleDto dto);
    VehicleVo vehicledetail(VehicleEntity entity);
    int likes(VehicleDto dto);
    int dellikes(VehicleDto dto);
    int jimchk(VehicleDto dto);
    VehicleDto selMaxPageVal(VehicleDto dto);
    List<VehicleVo> vehicleSearchList2(ListSearchEntity list);
    int likeCount(VehicleDto dto);
    int likeMinus(VehicleDto dto);
    List<VehicleVo> selLikeLank();
    int hitsCount(VehicleEntity entity);
    List<VehicleVo> selhitsLank();
}