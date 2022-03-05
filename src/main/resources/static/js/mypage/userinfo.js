const form = document.querySelector('.userinfo_modify_container');
if(form) {
    const upw = form.querySelector('input[name="upw"]')
    const newupw = form.querySelector('input[name="newupw"]')
    const rePassword = form.querySelector('input[name="rePassword"]')
    //8~15자 영문자, 숫자, 특수문자 포함
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/;
    form.addEventListener('submit', e => {
        if((upw.value === '' || newupw.value === '') || rePassword.value === ''){
            e.preventDefault();
            alert('빈칸을 채워주세요.');
        } else if(!pwRegex.test(newupw.value)){
            e.preventDefault();
            alert('비밀번호는 영문자, 숫자, 특수문자 포함 8~15글자입니다.')
        } else if(newupw.value !== rePassword.value){
            e.preventDefault();
            alert('새 비밀번호가 서로 다릅니다.');
        } else {
            e.preventDefault();
            fetch('/user/pasChk', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    upw : upw.value,
                    newupw : newupw.value,
                }),
            }).then(res => res.json())
                .then(data => {
                    switch (data.result) {
                        case 2: alert('현재 비밀번호가 다릅니다.'); break;
                        default: alert('비밀번호 변경에 성공하였습니다.');
                        location.href='/mypage/userinfo';
                        break;
                    }
                })
        }
    })
}