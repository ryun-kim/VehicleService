const writeBtn = document.querySelector('#writeBtn');
const modBtn = document.querySelector('#modBtn');

/*--------------------------클릭했을시 글쓰기페이지로 이동-----------------------------------*/
writeBtn.addEventListener('click', () => {
    $("section").load("/center/write");
})

/*--------------------------클릭했을시 글쓰기페이지로 이동-----------------------------------*/
modBtn.addEventListener('click', () => {
    $("section").load("/center/write");
})