const idFindForm = document.querySelector('#idFindForm');
const pasFindForm = document.querySelector('#pasFindForm');

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
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                    })
                    const verifyLabelBox = idFindForm.querySelector('#verifyLabelBox');
                    const verifyInputBox = idFindForm.querySelector('#verifyInputBox');
                    const verifyBox = idFindForm.querySelector('#verifyBox');
                    const idText = idFindForm.querySelector('#idText');
                    const h4 = document.createElement('h4');
                    const inputCode = document.createElement('input');
                    const inputBtn = document.createElement('input');

                    h4.innerText = '인증코드';
                    verifyLabelBox.appendChild(h4);

                    inputCode.type = 'text';
                    inputCode.className = 'form-control border border-info';
                    inputCode.placeholder = '인증코드를 입력해주세요.';
                    verifyInputBox.appendChild(inputCode);

                    inputBtn.type = 'button'
                    inputBtn.className = 'col-md-2 btn btn-secondary';
                    inputBtn.value = '아이디찾기';
                    verifyBox.appendChild(inputBtn);

                    inputBtn.addEventListener('click', () => {
                        fetch(`/service/verifyCode/${inputCode.value}/${idFindForm.idEmailInput.value}`, {
                            method: "POST",
                            headers: {
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
    if(pasFindForm.idInput.value === ''){
        e.preventDefault();
        alert('아이디를 입력해주세요.')
    } else if(pasFindForm.pasPhoneInput.value === ''){
        e.preventDefault();
        alert('이메일을 입력해주세요.')
    }
})