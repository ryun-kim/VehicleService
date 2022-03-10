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
    public List<VehicleVo> selBoardList(VehicleDto dto) { //국산  리스트
        dto.setCategory("국산");
        return service.vehicleList(dto);
    }

//    @ResponseBody
//    @GetMapping("/sortlist")
//    public List<VehicleDto> mangham(@RequestParam("test") String test){
//         VehicleDto dto = new VehicleDto();
//         dto.setTest(test);
////         List<VehicleDto> list = service.
//
//    }

    @GetMapping("/forlist")
    public List<VehicleVo> selBoardforList(VehicleDto dto) { //수입 리스트
        dto.setCategory("수입");
        return service.vehicleList(dto);
    }

    @GetMapping("/homSearch")
    public List<VehicleVo> homeSearchList(VehicleEntity entity){
        List<VehicleVo> list = service.homeSearchList(entity);
        System.out.println(list);
        return list;
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
        return service.vehicleList2(dto);
    }

    @GetMapping("/searchList")
    public List<VehicleVo>  searchList(ListSearchEntity list){
        return service.searchList(list);
    }


}
