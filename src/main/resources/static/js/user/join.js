/*----------------------------------- 달력 생성 ----------------------------------------*/
const cmbyear = document.querySelector('#cmbyear');
const cmbmonth = document.querySelector('#cmbmonth');
const cmbday = document.querySelector('#cmbday');

for (let i = 1970; i <= 2022; i++) {
    cmbyear.options[i - 1970] = new Option(i, i); //new option(실제값, value)
}

for (let i = 0; i < 12; i++) {
    cmbmonth.options[i] = new Option(i + 1, i + 1);
}

cmbyear.options[new Date().getFullYear() - 1970].selected = "seleted"
cmbmonth.options[new Date().getMonth()].selected = "seleted" //getMonth() : 1월~12월 > 리턴0~11

function getLastDay(year, month) {
    let lastDay = new Date(year, month + 1);
    lastDay.setDate(0);
    // console.log(lastDay.getDate()); //28
    return lastDay.getDate(); //마지막날짜 리턴
    //ex : 2월달의 마지막일을 알고싶다면 XX년 1월 1일로 설정한 후 setDate에 인자로 0을 주면 된다.
    // Date객체는 알아서 월간 이동하고 2월의 마지막일로 설정된다. getDate()는 일(몇일)을 가져옴
}

function set_day() {
    let year = parseInt(cmbyear.options[cmbyear.selectedIndex].value); //value는 기본이 string이라 number로 형변환, number는 콘솔에서 보라색
    let month = parseInt(cmbmonth.options[cmbmonth.selectedIndex].value - 1); //실제값말고 value가져옴
    //날짜간 덧셈, 뺄샘 과정이 일어날 수 있음으로 number로 형변환
    // console.log(year, month); //2022, 1
    let lastDay = getLastDay(year, month);
    for (let i = 0; i <= lastDay - 1; i++) {
        cmbday.options[i] = new Option(i + 1, i + 1);
    }
}

set_day();

function m_change() {
    cmbday.options.length = 0;
    set_day();
}

/*---------------------------- 조건 체크 -----------------------------------*/
const form = document.querySelector('form');
if (form) {
    //8~15자 영문자, 숫자, 특수문자 포함
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/;
    //4~15자 영문자, 숫자 포함
    const idRegex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{4,15}$/;
    const blank_pattern = /[\s]/g; //공백금지
    const idBtnChk = form.querySelector('#idBtnChk');
    const idChkMsg = form.querySelector('#idChkMsg');
    let idChkState = 2; //2: 체크 안함, 1: 아이디 사용가능, 0: 아이디 사용 불가능

    idBtnChk.addEventListener('click', () => {
        const idVal = form.uid.value; //얘가 밖에있으면 당연히 아무값이 안찍힌다.
        if(!idRegex.test(idVal)){
            alert('아이디는 영문자, 숫자 포함 4~15글자입니다.')
            return;
        }
        fetch(`/user/idChk/${idVal}`)
            .then(res => res.json())
            .then((data) => {
                idChkState = data.result; //1: 아이디 사용가능, 0: 아이디 사용 불가능
                if(data.result === 1){
                    if(idChkMsg.classList.contains('textred')){
                        idChkMsg.classList.remove('textred');
                    }
                    idChkMsg.classList.add('textgreen');
                    idChkMsg.innerText = '사용할 수 있는 아이디 입니다.'
                } else {
                    if(idChkMsg.classList.contains('textgreen')){
                        idChkMsg.classList.remove('textgreen');
                    }
                    idChkMsg.classList.add('textred');
                    idChkMsg.innerText = '이미 사용중인 아이디 입니다.'
                }
            }).catch((e) => {
            console.log(e);
        });
    });

    form.addEventListener('submit', (e) => {
        if (form.uid.value === '' || blank_pattern.test(form.uid.value) === true) { //공백이있다면 true리턴
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if (form.upw.value === '' || blank_pattern.test(form.upw.value) === true) {
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if (form.rePassCheck.value === '' || blank_pattern.test(form.rePassCheck.value) === true) {
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if (form.nm.value === '' || blank_pattern.test(form.nm.value) === true) {
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if (form.phnum.value === '' || blank_pattern.test(form.phnum.value) === true) {
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if (!pwRegex.test(form.upw.value)) {
            e.preventDefault();
            alert('비밀번호는 영문자, 숫자, 특수문자 포함 8~15글자입니다.')
        } else if (form.upw.value !== form.rePassCheck.value) {
            e.preventDefault();
            alert('비밀번호가 서로 다릅니다.')
        } else if(idChkState !== 1){
            switch (idChkState) {
                case 2: e.preventDefault(); alert('아이디 중복체크를 해주세요.'); break;
                case 0: e.preventDefault(); alert('다른 아이디를 사용해주세요.'); break;
            }
        } else if (!form.agree.checked) {
            e.preventDefault();
            alert('약관에 동의해주세요.')
        } else {
            alert('회원가입에 성공하였습니다.')
        }
    })

    //change: 다른곳으로 포커스가 이동이될때 이벤트 발생 keyup: 글자를 칠때 이벤트 발생
    form.uid.addEventListener('keyup', () => {
        idChkState = 2;
        idChkMsg.innerText = '';
    });
}
