const idFindForm = document.querySelector('#idFindForm');
const pasFindForm = document.querySelector('#pasFindForm');
const modal = document.getElementById("modal");
const closeBtn = modal.querySelector(".close-area");
const modalForm = document.querySelector('#modalForm');

idFindForm.addEventListener('submit', (e) => {
    if(idFindForm.idEmailInput.value === ''){
        alert('이메일을 입력해주세요.')
    } else {
        fetch(`/user/emailChk/${idFindForm.idEmailInput.value}`)
            .then(res => res.json())
            .then(data => {
                if (data.result === 0) {
                    alert('가입되어 있지않은 회원입니다.')
                } else {
                    alert('인증코드를 성공적으로 보냈습니다.')
                    fetch(`/service/mail/${idFindForm.idEmailInput.value}`, {
                        'method' : "POST",
                        'headers' : {
                            "Content-Type": "application/json",
                        },
                    })
                    const verifyLabelBox = idFindForm.querySelector('.verifyLabelBox');
                    const verifyInputBox = idFindForm.querySelector('.verifyInputBox');
                    const verifyBox = idFindForm.querySelector('.verifyBox');
                    const idText = idFindForm.querySelector('.idText');
                    const h4 = document.createElement('h4');
                    const inputCode = document.createElement('input');
                    const inputBtn = document.createElement('input');

                    h4.innerText = '인증코드';
                    verifyLabelBox.appendChild(h4);

                    inputCode.type = 'text';
                    inputCode.className = 'form-control border border-info';
                    inputCode.placeholder = '인증코드를 입력해주세요.';
                    verifyInputBox.appendChild(inputCode);

                    inputBtn.type = 'button';
                    inputBtn.className = 'col-md-2 btn btn-secondary';
                    inputBtn.value = '아이디찾기';
                    verifyBox.appendChild(inputBtn);

                    inputBtn.addEventListener('click', () => {
                        fetch(`/service/verifyCode/${inputCode.value}/${idFindForm.idEmailInput.value}`, {
                            'method' : "POST",
                            'headers' : {
                                "Content-Type": "application/json",
                            },
                        }).then(res => res.json())
                            .then(data => {
                                if(data.result === '0'){
                                    alert('인증코드를 잘못입력하였습니다.')
                                } else {
                                    idText.innerText = '회원님의 아이디는 ' + data.id + ' 입니다.';
                                }
                            })
                    })
                }
            }).catch((e) => {
            console.log(e);
        });
    }
    e.preventDefault();
})

pasFindForm.addEventListener('submit', (e) => {
    const idVal = pasFindForm.idInput.value;
    const emailVal = pasFindForm.pasEmailInput.value;

    if(idVal === ''){
        e.preventDefault();
        alert('아이디를 입력해주세요.')
    } else if(emailVal === ''){
        e.preventDefault();
        alert('이메일을 입력해주세요.')
    } else {
        fetch(`/user/emailIdChk`, {
            'method': 'post',
            'headers': { 'Content-Type': 'application/json' },
            'body': JSON.stringify({
                'uid' : idVal,
                'email' : emailVal
            }),
        }).then(res => res.json())
            .then(data => {
                switch (data.result) {
                    case 0: alert('아이디 또는 이메일을 잘못입력하였습니다.'); break;
                    case 1: alert('인증코드를 성공적으로 보냈습니다');
                            fetch(`/service/mail/${emailVal}`, {
                                'method' : "POST",
                                'headers' : {
                                    "Content-Type": "application/json",
                                },
                            })
                        const verifyLabelBox = pasFindForm.querySelector('.verifyLabelBox');
                        const verifyInputBox = pasFindForm.querySelector('.verifyInputBox');
                        const verifyBox = pasFindForm.querySelector('.verifyBox');
                        const idText = pasFindForm.querySelector('.idText');
                        const h4 = document.createElement('h4');
                        const inputCode = document.createElement('input');
                        const inputBtn = document.createElement('input');

                        h4.innerText = '인증코드';
                        verifyLabelBox.appendChild(h4);

                        inputCode.type = 'text';
                        inputCode.className = 'form-control border border-info';
                        inputCode.placeholder = '인증코드를 입력해주세요.';
                        verifyInputBox.appendChild(inputCode);

                        inputBtn.type = 'button';
                        inputBtn.className = 'col-md-2 btn btn-secondary';
                        inputBtn.value = '비밀번호찾기';
                        verifyBox.appendChild(inputBtn);

                        inputBtn.addEventListener('click', () => {
                            fetch(`/service/verifyCode/${inputCode.value}/${emailVal}`, {
                                'method' : "POST",
                                'headers' : {
                                    "Content-Type": "application/json",
                                },
                            }).then(res => res.json())
                                .then(data => {
                                    if(data.result === '0'){
                                        alert('인증코드를 잘못입력하였습니다.')
                                    } else {
                                        modalOn()
                                    }
                                })
                        })
                        break;
                }
            }).catch((e) => {
            console.log(e);
        });
    }
    e.preventDefault();
})

modalForm.addEventListener('submit', (e) => {
    //8~15자 영문자, 숫자, 특수문자 포함
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/;
    const blank_pattern = /[\s]/g; //공백금지
    if(modalForm.upw.value === '' || modalForm.rePasChk.value === ''){
        e.preventDefault();
        alert('비밀번호를 입력해주세요.')
    } else if(blank_pattern.test(modalForm.upw.value) === true || blank_pattern.test(modalForm.rePasChk.value) === true){
        e.preventDefault();
        alert('비밀번호를 입력해주세요.')
    } else if(!pwRegex.test(modalForm.upw.value)){
        e.preventDefault();
        alert('비밀번호는 영문자, 숫자, 특수문자 포함 8~15글자입니다.')
    } else if(modalForm.upw.value !== modalForm.rePasChk.value){
        e.preventDefault();
        alert('비밀번호가 서로 다릅니다.')
    } else {
        alert('비밀번호 변경에 성공하였습니다.')
    }
})

function modalOn() {
    modal.style.display = "flex"
}
function isModalOn() {
    return modal.style.display === "flex"
}
function modalOff() {
    modal.style.display = "none"
}

closeBtn.addEventListener("click", e => {
    modalOff()
})

modal.addEventListener("click", e => {
    const evTarget = e.target
    if(evTarget.classList.contains("modal-overlay")) {
        modalOff()
    }
})

window.addEventListener("keyup", e => {
    if(isModalOn() && e.key === "Escape") {
        modalOff()
    }
})