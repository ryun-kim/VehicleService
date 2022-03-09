package com.koreait.vehicleservice.chat;

import com.koreait.vehicleservice.MyUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/chat")
public class MainController {
    @Autowired MainService service;
    List<Room> roomList = new ArrayList<>(); //방리스트
    @Autowired MyUserUtils userUtils;

    @GetMapping("/chat")
    public String chat(Room room,Model model){
        if(userUtils.getLoginUser()==null){
            return "redirect:/home";
        }
        model.addAttribute("roomNumber",room.getRoomNumber());
        return "/chat/chat";
    }


    @GetMapping("/getRoom")
    @ResponseBody
    public List<Room> getRoom(){
        return roomList;
    }


    @GetMapping("/moveChating") //직접치는거 방지? 머그런거같던데 일단 보류
    public String chating(Room room) throws UnsupportedEncodingException {
//        int roomNumber = Integer.parseInt((String) params.get("roomNumber"));
//        List<Room> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
//        if(new_list != null && new_list.size() > 0) {
//           String roomName =(String)params.get("roomName");
        String RoomName = URLEncoder.encode(room.getRoomName(), "UTF-8");
            return "redirect:/chat/chat?roomName="+RoomName+"&"+"roomNumber="+room.getRoomNumber();
//        }else {
//            return "/room";
//        }
    }

    @PostMapping("/createChatting")
    @ResponseBody
    public int createChatting(@RequestBody Room room){
        return  service.createChatting(room); //생성된 방번호 리턴
    }
}
