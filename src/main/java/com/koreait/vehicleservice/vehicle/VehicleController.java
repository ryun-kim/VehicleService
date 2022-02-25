package com.koreait.vehicleservice.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("vehicle")
public class VehicleController {
    @Autowired private VehicleService service;
    @GetMapping("/list")
    public void list() {}

    @GetMapping("/list/for")
    public void listfor(){}

    @GetMapping("/detail")
    public String detail(VehicleEntity entity,Model model){
        entity.setSelliboard(11);//일단 고정 test
        model.addAttribute("data", service.vehicledetail(entity));
        return "redirect:detail";
    }

    @GetMapping("/write")
    public void write(){

    }

    @PostMapping("/write")
    public String inVehicle(VehicleDto dto, @RequestParam MultipartFile mainimg){
        VehicleEntity entity = dto;
        service.inVehicle(entity);//판매글 db입력
        service.inOptions(dto); //옵션 db입력
        service.inExplanation(dto);//설명글 db입력
        service.uploadMainImg(mainimg); //메인이미지 db입력
        service.uploadSubImg(dto.getSubimg()); //서브이미지 db입력

        return "redirect:list";
    }


    @GetMapping("/carNumChk/{car_num}")
    @ResponseBody
    public Map<String, Integer> selCarNum(@PathVariable String car_num){
        Map<String, Integer> res = new HashMap<>();
        System.out.println(car_num);
        int a = service.selCarNum(car_num);
        System.out.println(a);
        res.put("result", a);
        return res;
    }

}