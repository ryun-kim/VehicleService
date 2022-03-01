const writeBtn = document.querySelector('#writeBtn');
const modBtn = document.querySelector('#modBtn');
const delBtn = document.querySelector('#delBtn');
const loginUserInfrm = document.querySelector('.loginUserInfrm');
const data = document.querySelector('#data');
const prevBtn = document.querySelector('#prevBtn');
const nextBtn = document.querySelector('#nextBtn');

/*--------------------------클릭했을시 글쓰기페이지로 이동-----------------------------------*/
writeBtn.addEventListener('click', () => {
    if(loginUserInfrm === null) {
        alert('로그인해야 이용할 수 있는 서비스입니다.')
        location.href='/user/login';
    } else {
        $("section").load("/center/write?quesiboard=0");
    }
})


/*--------------------------클릭했을시 글쓰기페이지로 이동-----------------------------------*/
if(modBtn) {
    modBtn.addEventListener('click', () => {
        $("section").load(`/center/write?quesiboard=${data.dataset.quesiboard}`);
    })
}

if(delBtn){
    delBtn.addEventListener('click', () => {
        if(confirm('정말 삭제 하시겠습니까?')) {
            location.href = `/center/del/${data.dataset.quesiboard}`;
        }
    })
}

if(prevBtn){
    prevBtn.addEventListener('click', () => {
        $("section").load(`/center/detailquestion?quesiboard=${prevBtn.dataset.prevquesiboard}`);
    })
}

if(nextBtn){
    nextBtn.addEventListener('click', () => {
        $("section").load(`/center/detailquestion?quesiboard=${nextBtn.dataset.nextquesiboard}`);
    })
}