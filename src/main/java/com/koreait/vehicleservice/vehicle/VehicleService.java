package com.koreait.vehicleservice.vehicle;

import com.koreait.vehicleservice.vehicle.model.BoardEntity;
import com.koreait.vehicleservice.vehicle.model.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleMapper mapper;

    public List<BoardVo> vehicleList(BoardEntity entity){

        return mapper.vehicleList(entity);
    }


}
