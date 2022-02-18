package com.koreait.vehicleservice.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ajax/vehicle")
public class VehicleRestController {
    @Autowired
    private VehicleService service;

    @GetMapping("/{category}")
    public List<VehicleVo> selBoardList(@PathVariable String category, VehicleDto vo) {
        vo.setCategory(category);
        return service.vehicleList(vo);
    }
}
