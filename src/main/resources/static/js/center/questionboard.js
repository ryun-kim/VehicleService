const boardContainer = document.querySelector('.boardContainer');
const trArr = boardContainer.querySelectorAll('tr');
const writeBtn = boardContainer.querySelector('button');
const loginUserInfrm = boardContainer.querySelector('.loginUserInfrm');
const pageContainerElem = document.querySelector('#pageContainer');

let currentPage = 1; //현재 페이지
let maxPage = 1;
const recordCount = 6; //레코드 수
const pagingCount = 5; //페이징의 페이징 수

//클릭했을시 상세페이지로 이동
trArr.forEach(item => {
    item.addEventListener('click', () => {
        const quesiboard = $(item).find('td:eq(0)').text() //클릭했을때 그 행의 quesiboard값 들고온다.
        $("section").load(`/center/detailquestion?quesiboard=${quesiboard}`);
    })
})

//클릭했을시 글쓰기페이지로 이동
if(writeBtn) {
    writeBtn.addEventListener('click', () => {
        if (loginUserInfrm === null) {
            alert('로그인해야 이용할 수 있는 서비스입니다.')
            location.href = '/user/login';
        } else {
            $("section").load("/center/write?quesiboard=0"); //0일땐 detail 안거치고 바로 끌쓰기 페이지로 이동
        }
    })
}

//현재페이지 레코드개수에 맞게 리스트 정보 들고오기
const getList = () => {
    fetch('/center/getlist', {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            currentPage, recordCount
        }),
    }).then(res => res.json())
        .then(data => {
            makeRecodeList(data); //가지오온 리스트정보로 테이블 행을 만든다.
        })
}

//마지막 페이지 값(한번만 호출)
const getMaxPageVal = () => {
    myFetch.get(`/center/board/maxpage`, data => {
        maxPage = data.result;
        makePaging();
    }, {
        'recordCount' : recordCount
    })
}

getMaxPageVal();

//페이징 만들기
const makePaging = () => {
    const ulElem = pageContainerElem.querySelector('nav > ul');
    ulElem.innerHTML = null; //그전에 있던거 지운다

    const calcPage = parseInt((currentPage - 1) / pagingCount);
    const startPage = (calcPage * pagingCount) + 1;
    const lastPage = (calcPage + 1) * pagingCount;

    if(startPage > 1) { //왼쪽 꺽새 찍을지말지
        const liElem = document.createElement('li');
        ulElem.appendChild(liElem);

        liElem.className = 'page-item page-link pointer';
        liElem.innerHTML = '&lt;';
        liElem.addEventListener('click', e => {
            currentPage = startPage - 1;
            getList();
            makePaging();
        })
    }

    for(let i=startPage; i<=(lastPage > maxPage ? maxPage : lastPage); i++) { //중간부분
        const liElem = document.createElement('li');
        ulElem.appendChild(liElem);

        liElem.className = 'page-item page-link pointer';
        liElem.innerText = i;
        liElem.addEventListener('click', e => {
                currentPage = i;
                getList();
        });
    }

    if(maxPage > lastPage) { //오른쪽 꺽새 찍을지말지
        const liElem = document.createElement('li');
        ulElem.appendChild(liElem);

        liElem.className = 'page-item page-link pointer';
        liElem.innerHTML = '&gt;';
        liElem.addEventListener('click', e => {
            currentPage = lastPage + 1;
                getList();
                makePaging();
        });
    }
}

//테이블 행 만들기
const makeRecodeList = list => {
    const tbodyElem = boardContainer.querySelector('table tbody');
    tbodyElem.innerHTML = null; //그전에 있던거 지운다
    list.forEach(item => {
        const trElem = document.createElement('tr');
        tbodyElem.appendChild(trElem); //append 순서는 상관없다.
        trElem.innerHTML = `
                <td>${item.quesiboard}</td>
                <td>${item.title}</td>
                <td>${item.hits}</td>
                <td>${item.isanswer}</td>
                `;
        trElem.classList.add('pointer');
        trElem.addEventListener('click', () => {
            const quesiboard = item.quesiboard; //클릭했을때 행의 quesiboard값 들고온다.
            $("section").load(`/center/detailquestion?quesiboard=${quesiboard}`);
        })
    });
}



