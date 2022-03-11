{

    $( document ).ready( function() {   //해당 위치로 스크롤 이동
        const header_bar = document.querySelector(".header_bar");
        const elMenus = header_bar.querySelectorAll("div");
        elMenus.forEach(function(elMenu) {
            elMenu.addEventListener("click", function() {

                const idx = Array.from(elMenus).indexOf(elMenu);

                let sx = document.querySelector(`.main>div:nth-child(${idx+1})`).offsetTop;
                document.querySelector('html').scroll({
                    left: 0,
                    top: sx-100,//해당위치높이값에서 헤더높이만큼 뺌
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
    var selliboard = document.querySelector('#selliboard').value;
    const likeBtn = document.querySelector('#likeBtn'); //좋아요 버튼 표시

    myFetch.get(`/ajax/vehicle/sellike?selliboard=`+selliboard,data=>{
        switch (data){
            case 1:
                likeBtn.innerHTML = `                 
                                <button type="button" class="btn jjimBtn btn-outline-danger" onclick="jjimEvent(this);">
                                    <i class="fa-solid fa-heart"></i>좋아요
                                </button>`;
                break;
            case 0:
                likeBtn.innerHTML = `                 
                                <button type="button" class="btn jjimBtn" onclick="jjimEvent(this);">
                                    <i class="fa-regular fa-heart"></i>좋아요
                                </button>`;
                break;
            default:
                break;
        }
    });

    function jjimEvent(target){ //좋아요 이벤트

        let iElem = target.querySelector('i');
        if (iElem.classList.contains('fa-regular')){//찜
            iElem.classList.remove('fa-regular');
            iElem.classList.add('fa-solid');
            target.classList.add('btn-outline-danger');

            myFetch.get(`/ajax/vehicle/likes/`+selliboard);
        }else { //찜 취소
            iElem.classList.remove('fa-solid');
            iElem.classList.add('fa-regular');
            target.classList.remove('btn-outline-danger');

            myFetch.delete(`/ajax/vehicle/dellikes/`+selliboard);
        }
    }

//채팅하기 버튼
    var chattingBtn = document.querySelector('#chattingBtn');
    chattingBtn.addEventListener('click',()=>{
        var boardTitle = document.querySelector('#boardTitle').innerHTML;
        var writeriuser = document.querySelector('#writeriuser').value;

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