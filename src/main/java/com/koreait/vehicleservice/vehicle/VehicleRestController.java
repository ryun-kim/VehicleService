package com.koreait.vehicleservice.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search")
    public List<VehicleVo> search(@RequestParam String searchVal){
        System.out.println(searchVal);
        VehicleDto dto = new VehicleDto();
        dto.setSearchText(searchVal);
        return service.vehicleList(dto);
    }
}
