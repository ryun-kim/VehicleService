package com.koreait.vehicleservice.chat;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
     int inchattingList(Room room);
     int selRoomNumber(Room room);
     int inchattingUser(Room room);
     List<ChattingRoom> selCattingRoom(Room room);
     int inChattingRoom(ChattingRoom chroom);


}
