/*----------------------------------- 달력 생성 ----------------------------------------*/
window.onload = function () { //처음페이지가 열릴때 동적으로 날짜를 select박스에 넣는다.
    let sYear = 1980;
    let eYear = 2022;
    let sMonth = 1;
    let eMonth = 12;
    let sDay = 1;
    let eDay = 31;

    let strYear = '';
    let strMonth = '';
    let strDay = '';

    for (let i = eYear; i >= sYear; i--) { //select랑 option같이쓴다.
        strYear += "<option value=" + i + ">" + i + "</option>";
    }

    for (let i = sMonth; i <= eMonth; i++) {
        strMonth += "<option value=" + i + ">" + i + "</option>";
    }

    for (let i = sDay; i <= eDay; i++) {
        strDay += "<option value=" + i + ">" + i + "</option>";
    }

    document.getElementById("year").innerHTML = strYear;
    document.getElementById("month").innerHTML = strMonth;
    document.getElementById("day").innerHTML = strDay;
}

/*---------------------------- 조건 체크 -----------------------------------*/
const form = document.querySelector('form');
if (form) {
    //최소 8자, 하나 이상의 문자, 하나의 숫자 및 하나의 특수 문자 정규식
    const pwRegex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/;
    const blank_pattern = /[\s]/g; //공백금지
    form.addEventListener('submit', (e) => {
        if(form.checkId.value === '' || blank_pattern.test(form.checkId.value) == true){ //공백이있다면 true리턴
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if(form.passCheck.value === '' || blank_pattern.test(form.passCheck.value) == true){
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if(form.rePassCheck.value === '' || blank_pattern.test(form.rePassCheck.value) == true){
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if(form.nmCheck.value === '' || blank_pattern.test(form.nmCheck.value) == true){
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if(form.pNumberCheck.value === '' || blank_pattern.test(form.pNumberCheck.value) == true){
            e.preventDefault();
            alert('체크된 항목은 필수사항입니다.')
        } else if(!pwRegex.test(form.passCheck.value)){
            e.preventDefault();
            alert('비밀번호는 영문자, 숫자, 특수문자 포함 8~15글자입니다.')
        } else if(form.passCheck.value !== form.rePassCheck.value){
            e.preventDefault();
            alert('비밀번호가 서로 다릅니다.')
        } else if(!form.agree.checked){
            e.preventDefault();
            alert('약관에 동의해주세요.')
        }
    })
}