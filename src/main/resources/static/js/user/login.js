let form = document.querySelector('form');
form.addEventListener('submit', (e) => {
    if (form.id.value === '') {
        e.preventDefault();
        alert('아이디를 입력하세요.');
    } else if (form.pw.value === '') {
        e.preventDefault();
        alert('비밀번호를 입력하세요.');
    }
})

