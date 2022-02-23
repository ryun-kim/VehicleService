package com.koreait.vehicleservice.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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


    @PostMapping("/likes/selliboard={selliboard}")
    public void likes(VehicleDto dto,@PathVariable int selliboard){
        dto.setSelliboard(selliboard);
        service.likes(dto);
    }

    @PostMapping("/dellikes")
    public void dellikes(VehicleDto dto){
        service.dellikes(dto);
    }
}
