package com.koreait.vehicleservice.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired
    public VehicleService service;

    @GetMapping("/list")
    public void list() {}

    @GetMapping("/list/for")
    public void listfor(){}

    @GetMapping("/detail")
    public void detail(){

    }

    @GetMapping("/selPage")
    public void selPage(){

    }
}
