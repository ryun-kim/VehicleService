{
    const searchFrmElem = document.querySelector('#search_Result_Frm'); //검색어입력창
    const listorderElem = document.querySelector('#list_order'); // 정렬방법 만들어야함!!!

    const pageContainerElem = document.querySelector('#page_container');
    const ulElem = pageContainerElem.querySelector('nav > ul');

    let currentPage = 1; //현재 페이지
    let maxPage = 1;
    const recordCount = 6; //레코드 수
    const pagingCount = 5; //페이징의 페이징 수

    const iuser = document.querySelector('#iuser');

    if (searchFrmElem) { //검색창에 검색없으면 알람
        searchFrmElem.addEventListener('submit', () => {
            const searchVal = searchFrmElem.search_area.value;

            if (searchVal.length === 0) {
                alert('검색어를 입력해 주세요');
            }
        });
    }

    //찜버튼 활성화
    function jjimEvent(pk, target){
        let selliboard = pk;

        let iElem = target.querySelector('i');
        if (iElem.classList.contains('fa-regular')){//찜
            iElem.classList.remove('fa-regular');
            iElem.classList.add('fa-solid');
            target.classList.add('btn-outline-danger');

            myFetch.get(`/ajax/vehicle/likes/${selliboard}`,data=>{
                console.log(data);
            });
        }else { //찜 취소
            iElem.classList.remove('fa-solid');
            iElem.classList.add('fa-regular');
            target.classList.remove('btn-outline-danger');

            myFetch.delete(`/ajax/vehicle/dellikes/${selliboard}`);
        }
    }

    //글 리스트 정보 가져오기
    const getList = () => {
        myFetch.get(`/ajax/vehicle/list`, data => {
            makeRecordList(data);
        },{ currentPage, recordCount });
    }


    //마지막 페이지 값 (once)
    const getMaxPageVal = () => {
        myFetch.get(`/ajax/vehicle/maxpage`, data => {
            console.log(data.result);
            maxPage = data.result;
            makePaging();
        }, {recordCount});
    }
    getMaxPageVal();





    const makePaging = () => {
        ulElem.innerHTML = null;
        const calcPage = parseInt((currentPage - 1) / pagingCount);
        const startPage = (calcPage * pagingCount) + 1;
        const lastPage = (calcPage + 1) * pagingCount;
        if(startPage > 1) {
            makePagingItem('&lt;', () => {
                currentPage = startPage - 1;
                getList();
                makePaging();
            });
        }
        for(let i=startPage; i<=(lastPage > maxPage ? maxPage : lastPage); i++) {
            makePagingItem(i, () => {
                if(currentPage !== i) {
                    currentPage = i;
                    getList();
                }
            });
        }
        if(maxPage > lastPage) {
            makePagingItem('&gt;', () => {
                currentPage = lastPage + 1;
                getList();
                makePaging();
            });
        }
    }

    //페이징 item 만들기
    const makePagingItem = (val, cb) => {
        const liElem = document.createElement('li');
        liElem.className = 'page-item page-link pointer';
        liElem.innerHTML = val;
        liElem.addEventListener('click', cb);
        ulElem.appendChild(liElem);
    }

    //레코드 생성
    const makeRecordList = list => {
        const listdivElem = document.querySelector('.listdiv'); //결과창
        if(listdivElem){
            list.forEach(item =>{
                const resultdiv = document.createElement('div');
                resultdiv.className = "col";
                resultdiv.innerHTML = `
                    <div class="card shadow-sm bg-white h-100 " xmlns:c="http://www.w3.org/1999/html">
                        <input type="hidden" value="${item.selliboard}">
                        
                        <img class="card-img-top car_img" src="/vehicleImg/${item.selliboard}/${item.mainimg}" alt="이미지없음">
                        <div class="card-body">
                            <h4 class="card-title">${item.detail_model}</h4>
                            <p class="card-text">${item.price}만원</p>
                            <p class="card-text">${item.trading_area}</p>
                        </div>    
                    </div>
                `;
                let fullBtnDiv = document.createElement('div');
                fullBtnDiv.classList.add('g-4')
                fullBtnDiv.innerHTML = `
                            <div class="d-flex justify-content-end align-items-center">
                                <button type="button" class="btn jjimBtn" onclick="jjimEvent(${item.selliboard}, this);">
                                    <i class="fa-regular fa-heart"></i>좋아요
                                </button>
                            </div>`;
                let nonBtnDiv = document.createElement('div');
                nonBtnDiv.classList.add('g-4')
                nonBtnDiv.innerHTML = `
                            <div class="d-flex justify-content-end align-items-center">                            
                                <button type="button" class="btn jjimBtn btn-outline-danger" onclick="jjimEvent(${item.selliboard}, this);">
                                    <i class="fa-solid fa-heart"></i>좋아요
                                </button>
                            </div>`;
                if(iuser){
                    myFetch.get(`/ajax/vehicle/sellike?selliboard=${item.selliboard}`,data=>{
                        switch (data){
                            case 1:
                                resultdiv.querySelector('.card').append(nonBtnDiv)
                                break;
                            case 0:
                                resultdiv.querySelector('.card').append(fullBtnDiv)
                                break;
                        }
                    });
                }
                listdivElem.appendChild(resultdiv);
                window.sessionStorage.getItem("loginUser")
                const detailElem = resultdiv.querySelector('.card-title');
                const imgElem = resultdiv.querySelector('.car_img');
                detailevent(imgElem);
                detailevent(detailElem);

                function detailevent(param) {
                    param.addEventListener('click', () => {
                        location.href = `/vehicle/detail?selliboard=${item.selliboard}`;
                    })
                }
            })
        }
    }
    getList();
}