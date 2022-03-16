let form = document.querySelector('form');
const errMsgList = form.querySelectorAll('.errMsg');

//로그인 수행
form.addEventListener('submit', (e) => {
    if (form.uid.value === '') {
        e.preventDefault();
        alert('아이디를 입력하세요.');
    } else if (form.upw.value === '') {
        e.preventDefault();
        alert('비밀번호를 입력하세요.');
    }
})

//change: 다른곳으로 포커스가 이동이될때 이벤트 발생 keyup: 글자를 칠때 이벤트 발생
form.uid.addEventListener('keyup', () => {
    for(let errMsg of errMsgList) {
        errMsg.innerText = '';
    }
})

form.upw.addEventListener('keyup', () => {
    for(let errMsg of errMsgList) {
        errMsg.innerText = '';
    }
})
