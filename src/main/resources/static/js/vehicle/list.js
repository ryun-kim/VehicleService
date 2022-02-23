{
    const searchFrmElem = document.querySelector('#search_Result_Frm'); //검색어입력창
    const listorderElem = document.querySelector('#list_order'); // 정렬방법 만들어야함!!!

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
        console.log(target);
        if (target.firstChild.classList.contains('fa-solid')) { //찜
            target.firstChild.classList.remove('fa-solid');
            target.firstChild.classList.add('fa-regular');
            target.classList.remove('btn-outline-danger');
        } else {
            target.firstChild.classList.remove('fa-regular');
            target.firstChild.classList.add('fa-solid');
            target.classList.add('btn-outline-danger');
        }
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
                    <img class="card-img-top car_img" src="${item.mainimg}" alt="이미지없음">
                    <div class="card-body">
                        <h4 class="card-title">${item.detail_model}</h4>
                        <p class="card-text">${item.price}만원</p>
                        <p class="card-text">${item.trading_area}</p>
                    </div>
                    <div class="g-4">
                        <div class="d-flex justify-content-end align-items-center">
                        <button type="button" class="btn jjimBtn" onclick="jjimEvent(${item.selliboard}, this);"><i class="fa-regular fa-heart"></i>좋아요</button>
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