package com.koreait.vehicleservice.chat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

@Getter
@Setter
public class ChattingRoom {
    private int roomNumber;
    private String ctnt;
    private String rdt;
    private int chatNumber;
    private int iuser;
    private String sessionID;
    private WebSocketSession session; //웹소켓 정보저장
}

