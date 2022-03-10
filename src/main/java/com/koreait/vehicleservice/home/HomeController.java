package com.koreait.vehicleservice.home;

import com.koreait.vehicleservice.home.model.NewsEntity;
import com.koreait.vehicleservice.vehicle.VehicleService;
import com.koreait.vehicleservice.vehicle.VehicleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired private NewsService service;
    @Autowired private VehicleService vehicleService;

    @GetMapping("/home")
    public void home(NewsEntity entity, Model model) throws Exception {
        model.addAttribute("newsList", service.getData(entity));
        model.addAttribute("likeLank", vehicleService.selLikeLank());
        model.addAttribute("hitsLank", vehicleService.selhitsLank());
    }

    @GetMapping("/home/likelank")
    @ResponseBody
    public List<VehicleVo> likelank() {
        return vehicleService.selLikeLank();
    }

    @GetMapping("/home/hitslank")
    @ResponseBody
    public List<VehicleVo> hitslank() {
        return vehicleService.selhitsLank();
    }
}
