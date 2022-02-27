const boardContainer = document.querySelector('.boardContainer');
const trArr = boardContainer.querySelectorAll('tr');
const writeBtn = boardContainer.querySelector('button');
const loginUserInfrm = boardContainer.querySelector('.loginUserInfrm');

/*--------------------------클릭했을시 상세페이지로 이동-----------------------------------*/
trArr.forEach(item => {
    item.addEventListener('click', () => {
        const quesiboard = $(item).find('td:eq(0)').text() //클릭했을때 행의 quesiboard값 들고온다.
        $("section").load(`/center/detailquestion?quesiboard=${quesiboard}`);
    })
})

/*--------------------------클릭했을시 글쓰기페이지로 이동-----------------------------------*/
writeBtn.addEventListener('click', () => {
    if(loginUserInfrm === null) {
        alert('로그인해야 이용할 수 있는 서비스입니다.')
        location.href='/user/login';
    } else {
        $("section").load("/center/write?quesiboard=0");
    }
})
