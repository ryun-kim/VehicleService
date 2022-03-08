package com.koreait.vehicleservice.chat;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/chat")
public class MainController {
    List<Room> roomList = new ArrayList<Room>();
    static int roomNumber = 0;

    @GetMapping("/chat")
    public void chat(Room room,Model model){
        model.addAttribute("roomNumber",room.getRoomNumber());
    }

    @GetMapping("/room")
    public void room(){}

    @GetMapping("/createRoom")
    @ResponseBody
    public List<Room> createRoom(String roomName){
        if(roomName != null && !roomName.trim().equals("")) {
            Room room = new Room();
            room.setRoomNumber(++roomNumber);
            room.setRoomName(roomName);
            roomList.add(room);
        }
        return roomList;
    }

    @GetMapping("/getRoom")
    @ResponseBody
    public List<Room> getRoom(){
        return roomList;
    }


    @GetMapping("/moveChating")
    public String chating(@RequestParam HashMap<Object, Object> params, Model model) {
        int roomNumber = Integer.parseInt((String) params.get("roomNumber"));

        List<Room> new_list = roomList.stream().filter(o->o.getRoomNumber()==roomNumber).collect(Collectors.toList());
        if(new_list != null && new_list.size() > 0) {
           String roomName =(String)params.get("roomName");

            return "redirect:/chat/chat?roomName="+roomName+"&"+"roomNumber="+roomNumber;
        }else {
            return "/room";
        }
    }
}
