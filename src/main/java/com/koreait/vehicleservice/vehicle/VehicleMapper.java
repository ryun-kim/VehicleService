package com.koreait.vehicleservice.vehicle;

import com.koreait.vehicleservice.vehicle.model.BoardEntity;
import com.koreait.vehicleservice.vehicle.model.BoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VehicleMapper {
    List<BoardVo> vehicleList(BoardEntity entity);
}
