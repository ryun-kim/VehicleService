// const moreInfo = document.querySelector('#moreInfo');
// const menu = document.querySelector('#menu');
// const notice = document.querySelector('#notice');
const trArr = document.querySelectorAll('tr');
const writeBtn = document.querySelector('button');

/*--------------------------더보기 클릭했을시 공지사항페이지로 이동-----------------------------------*/
/*moreInfo.addEventListener('click', () => {
    $("section").load("/html/notice.html");
    Array.from(menu.children).forEach(
        v => v.classList.remove('selected')
    )
    notice.classList.add('selected');
})*/

/*--------------------------클릭했을시 상세페이지로 이동-----------------------------------*/
trArr.forEach(item => {
    item.addEventListener('click', () => {
        const quesiboard = $(item).find('td:eq(0)').text() //클릭했을때 행의 quesiboard값 들고온다.
        $("section").load(`/center/detailquestion?quesiboard=${quesiboard}`);
    })
})

/*--------------------------클릭했을시 글쓰기페이지로 이동-----------------------------------*/
writeBtn.addEventListener('click', () => {
    $("section").load("/center/write");
})