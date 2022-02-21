{
    const searchFrmElem = document.querySelector('#search_Result_Frm'); //검색어입력창
    const jjimElemArr = document.querySelectorAll('.jjimBtn'); //찜버튼
    const listorderElem = document.querySelector('#list_order'); // 정렬방법 만들어야함!!!

    if (searchFrmElem) { //검색창에 검색없으면 알람
        searchFrmElem.addEventListener('submit', () => {
            const searchVal = searchFrmElem.search_area.value;

            if (searchVal.length === 0) {
                alert('검색어를 입력해 주세요');
            }

        });
    }

    if (jjimElemArr) { //좋아요버튼 활성화
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
        myFetch.get(`/ajax/vehicle/list`, list => {
            console.log(list);
            makeRecordList(list);
        });
    }

    //레코드 생성
    const makeRecordList = list => {
        const listdivElem = document.querySelector('.listdiv'); //결과창
        if(listdivElem){
            list.forEach(item =>{
                const resultdiv = document.createElement('div');
                resultdiv.className = "col";

                resultdiv.innerHTML = `
                <div class="card shadow-sm bg-white h-100">
                    <img class="card-img-top car_img" src="${item.mainimg}" alt="Card image cap">
                    <div class="card-body">
                        <h4 class="card-title">${item.detail_model}</h4>
                        <p class="card-text">${item.price}만원</p>                   
                        <p class="card-text">${item.trading_area}</p>
                    </div>
                    <div class="g-4">
                        <div class="d-flex justify-content-end align-items-center">
                        <button type="button" class="btn jjimBtn"><i class="fa-regular fa-heart"></i>좋아요</button>
                        </div>
                    </div>
                </div>
                `;
                listdivElem.appendChild(resultdiv);

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