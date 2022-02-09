/*----------------------------------- 달력 생성 ----------------------------------------*/
const cmbyear = document.querySelector('#cmbyear');
const cmbmonth = document.querySelector('#cmbmonth');
const cmbday = document.querySelector('#cmbday');

for (let i = 1970; i <= 2022; i++) {
    cmbyear.options[i - 1970] = new Option(i, i); //new option(실제값, value)
}

for (let i = 0; i < 12; i++) {
    cmbmonth.options[i] = new Option(i + 1, i);
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
    let year = parseInt(cmbyear.options[cmbyear.selectedIndex].value); //value는 기본이 문자열이라 숫자로 형변환
    let month = parseInt(cmbmonth.options[cmbmonth.selectedIndex].value); //실제값말고 value가져옴
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
    //최소 8자, 하나 이상의 문자, 하나의 숫자 및 하나의 특수 문자 정규식
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/;
    const blank_pattern = /[\s]/g; //공백금지
    form.addEventListener('submit', (e) => {
        if (form.checkId.value === '' || blank_pattern.test(form.checkId.value) == true) { //공백이있다면 true리턴
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if (form.passCheck.value === '' || blank_pattern.test(form.passCheck.value) == true) {
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if (form.rePassCheck.value === '' || blank_pattern.test(form.rePassCheck.value) == true) {
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if (form.nmCheck.value === '' || blank_pattern.test(form.nmCheck.value) == true) {
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if (form.pNumberCheck.value === '' || blank_pattern.test(form.pNumberCheck.value) == true) {
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if (!pwRegex.test(form.passCheck.value)) {
            e.preventDefault();
            alert('비밀번호는 영문자, 숫자, 특수문자 포함 8~15글자입니다.')
        } else if (form.passCheck.value !== form.rePassCheck.value) {
            e.preventDefault();
            alert('비밀번호가 서로 다릅니다.')
        } else if (!form.agree.checked) {
            e.preventDefault();
            alert('약관에 동의해주세요.')
        }
    })
}