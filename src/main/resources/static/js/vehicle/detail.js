{

    $( document ).ready( function() {   //해당 위치로 스크롤 이동
        const header_bar = document.querySelector(".header_bar");
        const elMenus = header_bar.querySelectorAll("div");
        elMenus.forEach(function(elMenu) {
            elMenu.addEventListener("click", function() {

                const idx = Array.from(elMenus).indexOf(elMenu);

                let sx = document.querySelector(`.container>div:nth-child(${idx+1})`).offsetTop;
                document.querySelector('html').scroll({
                    left: 0,
                    top: sx-70,//해당위치높이값에서 헤더높이만큼 뺌
                    behavior: "smooth"
                });
            });
        });


        $('.slider-for').slick({  //슬라이드 쇼
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: true,
            fade: false,

            prevArrow: $('.prev'),
            nextArrow: $('.next')
        });
        $('.slider-nav').slick({
            slidesToShow: 7,
            slidesToScroll: 1,
            asNavFor: '.slider-for',
            focusOnSelect: true
        });
    } );



    var chattingBtn = document.querySelector('#chattingBtn');
    chattingBtn.addEventListener('click',()=>{
        var boardTitle = document.querySelector('#boardTitle').innerHTML;
        var selliboard = document.querySelector('#selliboard').value;
        var writeriuser = document.querySelector('#writeriuser').value;

        console.log(boardTitle)
        myFetch.post(`/chat/createChatting`, roomNumber => {
            if(roomNumber !=0){ //구매자 판매자가 (iuser)가 다른경우
                location.href="/chat/moveChating?roomName="+boardTitle+"&"+"roomNumber="+roomNumber;
            }
        },{
            selliboard : selliboard,
            iuser : writeriuser  //글 작성자의 iuser
        });
    })
}