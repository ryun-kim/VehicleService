
window.onload = function(){
    getRoom();
    createRoom();
}

function getRoom(){
    myFetch.get(`/chat/getRoom`, result => {
        console.log(result)
        createChatingRoom(result);
    });
}

function createRoom(){
    $("#createRoom").click(function(){


        myFetch.get(`/chat/createRoom`, result => {
            createChatingRoom(result);
        },{	roomName : $('#roomName').val()	});

        $("#roomName").val("");
    });
}

function goRoom(number, name){
    location.href="/chat/moveChating?roomName="+name+"&"+"roomNumber="+number;
}

function createChatingRoom(res){
    if(res.size != 0){
        console.log(res)
        var tag = "<tr><th class='num'>순서</th><th class='room'>방 이름</th><th class='go'></th></tr>";
        res.forEach(item =>{
            var rn = item.roomName.trim();
            var roomNumber = item.roomNumber;
            tag += "<tr>"+
                "<td class='num'>"+roomNumber+"</td>"+
                "<td class='room'>"+ rn +"</td>"+
                "<td class='go'><button type='button' onclick='goRoom(\""+roomNumber+"\", \""+rn+"\")'>참여</button></td>" +
                "</tr>";
        });
        $("#roomList").empty().append(tag);
    }
}

// function commonAjax(url, parameter, type, calbak, contentType){
//     $.ajax({
//         url: url,
//         data: parameter,
//         type: type,
//         contentType : contentType!=null?contentType:'application/x-www-form-urlencoded; charset=UTF-8',
//         success: function (res) {
//             calbak(res);
//         },
//         error : function(err){
//             console.log('error');
//             calbak(err);
//         }
//     });
// }