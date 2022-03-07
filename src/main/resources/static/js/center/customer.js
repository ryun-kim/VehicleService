const board = document.querySelector('#board');
const guide = document.querySelector('#guide');
const introduction = document.querySelector('#introduction');
const notice = document.querySelector('#notice');
const menu = document.querySelector('#menu');

/*-------------------------- Jquery를 이용하여 section에 각html include -----------------------------*/
/*$(document).ready()는 문서가 준비되면 매개변수로 넣은 콜백 함수를 실행하라는 의미이다.
   jQuery 이벤트 메서드 중 하나이다. 이 메서드는 비슷한 기능을 수행한다.*/
$(document).ready(function () {
    $("section").load(`/center/questionboard?currentPage=1&recordCount=6`); //load에 url을 넣어줘야됨 controller거쳐서 html파일이 열림
    board.classList.add('selected');

    board.addEventListener('click', () => {
        $("section").load(`/center/questionboard?currentPage=1&recordCount=6`);
    })

    guide.addEventListener('click', () => {
        $("section").load("/center/guide");
    })

    introduction.addEventListener('click', () => {
        $("section").load("/center/introduction");
    })

    notice.addEventListener('click', () => {
        $("section").load("/center/notice?currentPage=1&recordCount=6");
    })
});

/*----------------------------------- 클릭했을시 li태그에 색상적용 ----------------------------------*/
/*array.from() 메서드는 유사 배열객체나 반복 가능한 객체를 복사해서 새로운 array객체를 만든다.
  ex) console.log(Array.from('foo') => expected output : Array['F', 'O', 'O'])*/
menu.addEventListener('click', e => {
    const selected = e.target;
    Array.from(menu.children).forEach(
        v => v.classList.remove('selected')
    )
    if (selected) {
        selected.classList.add('selected');
    }
})