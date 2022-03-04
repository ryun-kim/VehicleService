package com.koreait.vehicleservice.myPage;

import com.koreait.vehicleservice.vehicle.VehicleDto;
import com.koreait.vehicleservice.vehicle.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajax/mypage")
public class myPageRestController {
    @Autowired
    private myPageService service;

    @GetMapping("likes")
    public List<VehicleVo> likesList(VehicleDto dto){
        return service.likesList(dto);
    }

    @DeleteMapping("/dellikes")
    public int dellAllLike(VehicleDto dto) {return service.delAllLike(dto);}
}
