package com.koreait.vehicleservice.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vehicle")
public class VehicleController {
@Autowired private VehicleService service;
    @GetMapping("/list")
    public void list() {}


    @GetMapping("/detail")
    public void detail(){

    }

    @GetMapping("/write")
    public void write(){

    }

    @PostMapping("/write")
    public String inVehicle(VehicleDto dto){
        service.inVehicle(dto);
//        String fileNm = service.uploadMainImg(dto.getMainimg());
            return "redirect:list";


    }
}
