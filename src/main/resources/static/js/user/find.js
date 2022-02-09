const idFindForm = document.querySelector('#idFindForm');
const pasFindForm = document.querySelector('#pasFindForm');

idFindForm.addEventListener('submit', (e) => {
    if(idFindForm.idPhoneInput.value === ''){
        e.preventDefault();
        alert('휴대폰 번호를 입력해주세요.')
    }
})

pasFindForm.addEventListener('submit', (e) => {
    if(pasFindForm.idInput.value === ''){
        e.preventDefault();
        alert('아이디를 입력해주세요.')
    } else if(pasFindForm.pasPhoneInput.value === ''){
        e.preventDefault();
        alert('휴대폰 번호를 입력해주세요.')
    }
})