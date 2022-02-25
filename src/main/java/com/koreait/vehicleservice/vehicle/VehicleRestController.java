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

    @GetMapping("/list")
    public List<VehicleVo> selBoardList(VehicleDto vo) {
        return service.vehicleList(vo);
    }

    @GetMapping("/likes/{selliboard}")
    public int likes(@PathVariable int selliboard,VehicleDto dto){
        dto.setSelliboard(selliboard);
        return service.likes(dto);
    }

    @DeleteMapping("/dellikes/{selliboard}")
    public int dellikes(@PathVariable int selliboard,VehicleDto dto){
        dto.setSelliboard(selliboard);
        return service.dellikes(dto);
    }

    @GetMapping("/sellike")
    public int sellike(VehicleDto dto){
        return service.jimchk(dto);
    }

    @GetMapping("/maxpage")
    public VehicleDto selMaxPageVal(VehicleDto dto) {
        return service.selMaxPageVal(dto);
    }

    @GetMapping("/search")
    public List<VehicleVo> search(@RequestParam String searchVal){
        System.out.println(searchVal);
        VehicleDto dto = new VehicleDto();
        dto.setSearchText(searchVal);
        return service.vehicleList(dto);
    }
}
