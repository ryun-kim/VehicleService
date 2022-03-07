var ws;

function wsOpen(){
    //웹소켓 전송시 현재 방의 번호를 넘겨서 보낸다.
    ws = new WebSocket("ws://" + location.host + "/chating/"+$("#roomNumber").val());
    wsEvt();
}

function wsEvt() {
    ws.onopen = function(data){
        //소켓이 열리면 동작
    }

    ws.onmessage = function(data) {
        //메시지를 받으면 동작
        var msg = data.data;
        if(msg != null && msg.trim() != ''){
            var d = JSON.parse(msg);
            if(d.type == "getId"){
                var si = d.sessionId != null ? d.sessionId : "";
                if(si != ''){
                    $("#sessionId").val(si);
                }
            }else if(d.type == "message"){
                if(d.sessionId == $("#sessionId").val()){
                    $("#chating").append("<p class='me'>나 :" + d.msg + "</p>");
                }else{
                    $("#chating").append("<p class='others'>" + d.userName + " :" + d.msg + "</p>");
                }

            }else{
                console.warn("unknown type!")
            }
        }
    }

    document.addEventListener("keypress", function(e){
        if(e.keyCode == 13){ //enter press
            send();
        }
    });
}

function chatName(){
        wsOpen();
        $("#yourName").hide();
        $("#yourMsg").show();
}

function send() {
    var option ={
        type: "message",
        roomNumber: $("#roomNumber").val(),
        sessionId : $("#sessionId").val(),
        userName : $("#userName").val(),
        msg : $("#chatting").val()
    }
    ws.send(JSON.stringify(option))
    $('#chatting').val("");
}