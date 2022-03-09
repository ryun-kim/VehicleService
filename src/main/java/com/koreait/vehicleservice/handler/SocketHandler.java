package com.koreait.vehicleservice.handler;

import com.koreait.vehicleservice.MyUserUtils;
import com.koreait.vehicleservice.chat.ChattingRoom;
import com.koreait.vehicleservice.chat.MainService;
import com.koreait.vehicleservice.chat.Room;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class SocketHandler extends TextWebSocketHandler {
    @Autowired MainService service;
    @Autowired MyUserUtils userUtils;

   List<HashMap<String, Object>> rls = new ArrayList<>(); //웹소켓 세션을 담아둘 리스트

    List<ChattingRoom> list;// 채팅내역 리스트
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
        String msg = message.getPayload();
        JSONObject obj = jsonToObjectParser(msg);

        ChattingRoom chattingRoom = new ChattingRoom();

        String roomNumber = (String) obj.get("roomNumber");
        String iuser = (String) obj.get("iuser");
        String ctnt = (String) obj.get("msg");

        chattingRoom.setIuser(Integer.parseInt(iuser));
        chattingRoom.setRoomNumber(Integer.parseInt(roomNumber));
        chattingRoom.setCtnt(ctnt);

        service.inChattingRoom(chattingRoom);

        HashMap<String, Object> temp = new HashMap<String, Object>();
        if(rls.size() > 0) {
            for(int i=0; i<rls.size(); i++) {
                String rN = (String) rls.get(i).get("roomNumber"); //세션리스트의 저장된 방번호를 가져와서
                if(rN.equals(roomNumber)) { //같은값의 방이 존재한다면
                    temp = rls.get(i); //해당 방번호의 세션리스트의 존재하는 모든 object값을 가져온다.
                    break;
                }
            }

            //해당 방의 세션들만 찾아서 메시지를 발송해준다.
            for(String k : temp.keySet()) {
                if(k.equals("roomNumber")) { //다만 방번호일 경우에는 건너뛴다.
                    continue;
                }

                WebSocketSession wss = (WebSocketSession) temp.get(k);
                if(wss != null) {
                    try {
                        wss.sendMessage(new TextMessage(obj.toJSONString()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        //소켓 연결
        super.afterConnectionEstablished(session);
        boolean flag = false;
        String url = session.getUri().toString();
        String roomNumber = url.split("/chating/")[1];
        int idx = rls.size(); //방의 사이즈를 조사한다.
        if(rls.size() > 0) {
            for(int i=0; i<rls.size(); i++) {
                String rN = (String) rls.get(i).get("roomNumber");
                if(rN.equals(roomNumber)) {
                    flag = true;
                    idx = i;
                    break;
                }
            }
        }

        if(flag) { //존재하는 방이라면 세션만 추가한다.
            HashMap<String, Object> map = rls.get(idx);
            map.put(session.getId(), session);
        }else { //최초 생성하는 방이라면 방번호와 세션을 추가한다.
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("roomNumber", roomNumber);
            map.put(session.getId(), session);
            rls.add(map);
        }

        Room room = new Room();
        room.setRoomNumber(Integer.parseInt(roomNumber));
         list = service.selCattingRoom(room);

       if( list.size() != 0){ //이전 채팅내용이 하나라도 있는가

           //세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
           JSONObject obj = new JSONObject();
           JSONArray jArray = new JSONArray();//배열이 필요할때
           for (int i = 0; i < list.size(); i++)//배열
           {
               JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
               sObject.put("roomNumber", list.get(i).getRoomNumber());
               sObject.put("ctnt", list.get(i).getCtnt());
               sObject.put("rdt", list.get(i).getRdt());
               sObject.put("iuser", list.get(i).getIuser());
               jArray.put(sObject);
           }
           obj.put("type", "getChatting");
           obj.put("sessionId", session.getId());
           obj.put("list", jArray);//배열을 넣음
           session.sendMessage(new TextMessage(obj.toJSONString()));


       }else {
           //세션등록이 끝나면 발급받은 세션ID값의 메시지를 발송한다.
           JSONObject obj = new JSONObject();
           obj.put("type", "getId");
           obj.put("sessionId", session.getId());
           session.sendMessage(new TextMessage(obj.toJSONString()));
       }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        //소켓 종료
        if(rls.size() > 0) { //소켓이 종료되면 해당 세션값들을 찾아서 지운다.
            for(int i=0; i<rls.size(); i++) {
                rls.get(i).remove(session.getId());
            }
        }
        super.afterConnectionClosed(session, status);
    }

    private static JSONObject jsonToObjectParser(String jsonStr) {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        try {
            obj = (JSONObject) parser.parse(jsonStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return obj;
    }
}


