{

    const titleElem = document.querySelector('#mypage_title');
    const iuser = document.querySelector('.iuser');

    if(titleElem){ //마이페이지 돌아가기
        titleElem.addEventListener('click', ()=>{
            location.href=`/mypage/userinfo`;
        });
    }

    if(iuser ==null){ //비로그인상태에서 마이페이지 접근 시 로그인창으로
        location.href = `/user/login`;
    }
}