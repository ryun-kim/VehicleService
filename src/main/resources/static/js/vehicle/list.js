{
    const searchFrmElem = document.querySelector('#search_Result_Frm'); //검색어입력창
    const jjimElemArr = document.querySelectorAll('.jjimBtn'); //찜버튼
    const listorderElem = document.querySelector('#list_order'); // 정렬방법 만들어야함!!!
    const listdivElem = document.querySelector('.listdiv'); //결과창
    if (searchFrmElem) {
        searchFrmElem.addEventListener('submit', () => {
            const searchVal = searchFrmElem.search_area.value;

            if (searchVal.length === 0) {
                alert('검색어를 입력해 주세요');
            }

        });
    }

    if (jjimElemArr) {
        jjimElemArr.forEach(item => {
            const jjimHeart = item.firstChild;

            item.addEventListener('click', () => {
                if (jjimHeart.classList.contains('fa-solid')) {
                    jjimHeart.classList.remove('fa-solid');
                    jjimHeart.classList.add('fa-regular');
                    item.classList.remove('btn-outline-danger');
                } else {
                    jjimHeart.classList.remove('fa-regular');
                    jjimHeart.classList.add('fa-solid');
                    item.classList.add('btn-outline-danger');
                }
            });
        })
    }
    //글 리스트 정보 가져오기
    const getList = () => {
        myFetch.get(`/ajax/board/${icategory}`, list => {
            console.log(list);
            makeRecodeList(list);
        }, {currentPage, recordCount});
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
        const tbodyElem = listdivElem.querySelector('tbody');
        tbodyElem.className = 'col';
        tbodyElem.innerHTML = null;

        list.forEach(item => {
            const divElem = document.createElement('div');
            divElem.className = 'card shadow-sm bg-white h-100';
            tbodyElem.appendChild(divElem);

            divElem.innerHTML = `
                <img class="card-img-top car_img" th:src="${item.mainimg}" alt="Card image cap">
                <div class="card-body">
                    <h4 class="card-title">${item.detail_model}</h4>
                    <p class="card-text">${item.price}</p>
                    <p class="card-text">${item.explanations}</p>
                    <p class="card-text">${item.trading_area}</p>
                </div>
                <div class="g-4">
                    <div class="d-flex justify-content-end align-items-center">
                    <button type="button" class="btn jjimBtn"><i class="fa-regular fa-heart"></i>좋아요</button>
                    </div>
                </div>
            `;

            const detailElem = divElem.querySelector('.card-title');
            const imgElem = divElem.querySelector('.car_img');
            detailevent(imgElem);
            detailevent(detailElem);

            function detailevent(param) {
                param.addEventListener('click', () => {
                    location.href = `/vehicle/detail?selliboard=${item.selliboard}`;
                })
            }
        });

    }
}