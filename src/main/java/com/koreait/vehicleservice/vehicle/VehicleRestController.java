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


    @GetMapping("/likes/{selliboard}")
    public int likes(@PathVariable int selliboard,VehicleDto dto){
        dto.setSelliboard(selliboard);
        return service.likes(dto);
    }

    @DeleteMapping("/dellikes/{selliboard}")
    public void dellikes(@PathVariable int selliboard,VehicleDto dto){
        dto.setSelliboard(selliboard);
        service.dellikes(dto);
    }
}
