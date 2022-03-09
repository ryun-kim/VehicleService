package com.koreait.vehicleservice.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Room {
    int roomNumber;
    String roomName;

    int iuser;
    int selliboard;

    @Override
    public String toString() {
        return "Room [roomNumber=" + roomNumber + ", roomName=" + roomName + "]";
    }
}
