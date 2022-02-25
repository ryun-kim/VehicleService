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
    public void detail(VehicleEntity entity,Model model){
        model.addAttribute("vo", service.vehicledetail(entity));
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
        System.out.println(dto.getSubimg());
        service.uploadSubImg(dto.getSubimg()); //서브이미지 db입력

        String aa ="ffdfsfdss"+"\n"+"vuvu\n";

        return "redirect:list";
    }


    @GetMapping("/carNumChk/{car_num}")
    @ResponseBody
    public Map<String, Integer> selCarNum(@PathVariable String car_num){
        Map<String, Integer> res = new HashMap<>();
        int result = service.selCarNum(car_num);
        res.put("result", result);
        return res;
    }
}