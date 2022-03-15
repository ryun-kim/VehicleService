const writeBtn = document.querySelector('#writeBtn');
const modBtn = document.querySelector('#modBtn');
const delBtn = document.querySelector('#delBtn');
const loginUserInfrm = document.querySelector('.loginUserInfrm');
const data = document.querySelector('#data');
const prevBtn = document.querySelector('#prevBtn');
const nextBtn = document.querySelector('#nextBtn');
const form = document.querySelector('form');

//클릭했을시 글쓰기페이지로 이동
if(writeBtn) {
    writeBtn.addEventListener('click', () => {
        if (loginUserInfrm === null) {
            alert('로그인해야 이용할 수 있는 서비스입니다.')
            location.href = '/user/login';
        } else {
            $("section").load("/center/write?quesiboard=0"); //0일땐 detail 안거치고 바로 끌쓰기 페이지로 이동
        }
    })
}


//클릭했을시 수정페이지로 이동
if(modBtn) {
    modBtn.addEventListener('click', () => {
        $("section").load(`/center/write?quesiboard=${data.dataset.quesiboard}`);
    })
}

//클릭했을시 게시글 삭제
if(delBtn){
    delBtn.addEventListener('click', () => {
        if(confirm('정말 삭제 하시겠습니까?')) {
            location.href = `/center/del/${data.dataset.quesiboard}`;
        }
    })
}

//클릭했을시 이전글 이동
if(prevBtn){
    prevBtn.addEventListener('click', () => {
        $("section").load(`/center/detailquestion?quesiboard=${prevBtn.dataset.prevquesiboard}`);
    })
}

//클릭했을시 다음글 이동
if(nextBtn){
    nextBtn.addEventListener('click', () => {
        $("section").load(`/center/detailquestion?quesiboard=${nextBtn.dataset.nextquesiboard}`);
    })
}

//답변 등록
if(form){
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        if(form.cmtctnt.value === ''){
            alert('답변을 입력해주세요.')
        } else {
            fetch('/center/comment', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    quesiboard: data.dataset.quesiboard,
                    cmtctnt: form.cmtctnt.value,
                }),
            }).then(res => res.json())
                .then(data1 => {
                    if (data1.result === 1) {
                        $("section").load(`/center/detailquestion?quesiboard=${data.dataset.quesiboard}`);
                    }
                })
        }
    })
}
