const trArr = document.querySelectorAll('tr');
const writeBtn = document.querySelector('button');

/*--------------------------클릭했을시 상세페이지로 이동-----------------------------------*/
trArr.forEach(item => {
    item.addEventListener('click', () => {
        $("section").load("/center/detailnotice");
    })
})

/*--------------------------클릭했을시 글쓰기페이지로 이동-----------------------------------*/
writeBtn.addEventListener('click', () => {
    $("section").load("/center/write");
})