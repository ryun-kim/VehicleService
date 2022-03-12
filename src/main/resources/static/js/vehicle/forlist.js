{
    const searchFrmElem = document.querySelector('#search_Result_Frm'); //검색어입력창
    const pageContainerElem = document.querySelector('#page_container');
    const ulElem = pageContainerElem.querySelector('nav > ul');

    let currentPage = localStorage.getItem("currentPage");; //현재 페이지
    let maxPage = 1;
    const recordCount = 6; //레코드 수
    const pagingCount = 5; //페이징의 페이징 수

    const iuser = document.querySelector('#iuser');

    if (searchFrmElem) { //검색창에 검색없으면 알람
        searchFrmElem.addEventListener('submit', (e) => {
            const allCheckList = document.querySelectorAll('input[type="checkbox"]');
            allCheckList.forEach(item => {
                item.checked = false;
            }) //모든 select박스 체크 해제
            const searchVal = searchFrmElem.search_area.value;
            currentPage = 1; //현재 페이지
            localStorage.setItem("currentPage", currentPage);
            if (searchVal.length === 0) {
                ViewList= "AllList";
                localStorage.setItem("cast", null);
                getMaxPageVal("AllList");
                getList();
            } else {
                localStorage.setItem("cast", "modelSearch");
                ViewList= "modelSearch";
                localStorage.setItem("param", JSON.stringify({ 'searchVal' : searchVal}));
                getMaxPageVal("modelSearch");
                getList();
            }
            e.preventDefault();
        });
    }



    fetch("/json/MOCK_DATA.json") //json파일 가져오기
        .then(response => {
            return response.json();
        }).then(jsondata =>
        getSearchCompany(jsondata)
    );

    function getSearchCompany(json){
        var comList =json.result[1].companyList;
      const select_com = document.querySelector('#select_com>.accordion-body');
       for(var i=0; i<comList.length; i++) {
           let searchCompany = document.createElement('div');
           searchCompany.className = "form-check";
           searchCompany.innerHTML = `
                      <input class="form-check-input" type="checkbox" name="manufacturer" value="`+comList[i].company+`"  onchange="getSearchList()">
                        <label class="form-check-label" for="hyundai">
                       `+ comList[i].company+`
                        </label>
                `
           select_com.appendChild(searchCompany);
       }

    }



    function getSearchList() { //리스트 검색
        searchFrmElem.search_area.value = ''; //검색창 값 초기화
        currentPage = 1;
        localStorage.setItem("currentPage", currentPage);
            const com_query = 'input[name="manufacturer"]:checked';
        const compunyList = document.querySelectorAll(com_query);
        let compunyResult = [];
        compunyList.forEach((el) => {
            compunyResult.push(el.value)
        });

        const start_Mileage = document.getElementById('start_Mileage');
        let Min_Mileage= parseInt(start_Mileage.options[start_Mileage.selectedIndex].value);

        const end_Mileage = document.getElementById('end_Mileage');
        let Max_Mileage= parseInt(end_Mileage.options[end_Mileage.selectedIndex].value);

        const start_price = document.getElementById('start_price');
        let Min_price= parseInt(start_price.options[start_price.selectedIndex].value);

        const end_price = document.getElementById('end_price');
        let Max_price= parseInt(end_price.options[end_price.selectedIndex].value);

        const gearbox_query = 'input[name="gearbox"]:checked';
        const gearbox = document.querySelectorAll(gearbox_query);
        let gearboxResult = [];
        gearbox.forEach((el) => {
            gearboxResult.push(el.value)
        });

        const fuel_query = 'input[name="fuel"]:checked';
        const fuel = document.querySelectorAll(fuel_query);
        let fuelResult = [];
        fuel.forEach((el) => {
            fuelResult.push(el.value)
        });

        const aria_query = 'input[name="area"]:checked';
        const ariaList = document.querySelectorAll(aria_query);
        let ariaResult = [];
        ariaList.forEach((el) => {
            ariaResult.push(el.value)
        });

        ViewList= "sideSearch";
        var param ={
            'category' : "수입",
            'compunyResult' : compunyResult,
            'Min_Mileage' : Min_Mileage,
            'Max_Mileage' : Max_Mileage,
            'Min_price' : Min_price,
            'Max_price' : Max_price,
            'gearboxResult' : gearboxResult,
            'fuelResult' : fuelResult,
            'ariaResult' : ariaResult
        }
        localStorage.setItem("cast", "sideSearch");
        localStorage.setItem("param", JSON.stringify(param));
        getMaxPageVal("sideSearch");
        getList();
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
                // console.log(data);
            });
        }else { //찜 취소
            iElem.classList.remove('fa-solid');
            iElem.classList.add('fa-regular');
            target.classList.remove('btn-outline-danger');

            myFetch.delete(`/ajax/vehicle/dellikes/${selliboard}`);
        }
    }

    function getMaxPageVal(root) {  // maxpage를 가져옴
        if(root != null){
            switch (root) {
                case "AllList":  //국산버튼 클릭
                    var new_param = {
                        'currentPage': currentPage,
                        'recordCount': recordCount,
                        'category': "수입",
                        'root': "AllList"
                    };
                    break;
                case "home": //홈
                    var param = JSON.parse(localStorage.getItem("param"))
                    var new_param = {
                        'manufacturer': param.manufacturer,
                        'model': param.model,
                        'detail_model': param.detail_model,
                        'currentPage': currentPage,
                        'recordCount': recordCount,
                        'category': "수입",
                        'root': "home"
                    };
                    break;
                case "modelSearch":  //모델명
                    var param = JSON.parse(localStorage.getItem("param"))
                    var new_param = {
                        'searchVal' : param.searchVal,
                        'currentPage': currentPage,
                        'recordCount': recordCount,
                        'category': "수입",
                        'root': "modelSearch"
                    };
                    break;
                case "sideSearch":  //사이드 검색
                    var param = JSON.parse(localStorage.getItem("param"))
                    var new_param = {
                        'compunyResult' : param.compunyResult,
                        'Min_Mileage' : param.Min_Mileage,
                        'Max_Mileage' : param.Max_Mileage,
                        'Min_price' : param.Min_price,
                        'Max_price' : param.Max_price,
                        'gearboxResult' : param.gearboxResult,
                        'fuelResult' : param.fuelResult,
                        'ariaResult' : param.ariaResult,
                        'currentPage': currentPage,
                        'recordCount': recordCount,
                        'category': "수입",
                        'root': "sideSearch"
                    };
                    break;
            }
            if(root === "sideSearch"){
                myFetch.get(`/ajax/vehicle/sideSearchmaxpage`, data => {
                    maxPage = data.result;
                    console.log("페이지개수")
                    console.log(maxPage)
                    makePaging();
                }, new_param);
            }else{
                myFetch.get(`/ajax/vehicle/maxpage`, data => {
                    maxPage = data.result;
                    console.log("페이지개수")
                    console.log(maxPage)
                    makePaging();
                }, new_param);
            }
        }

    }

    var ViewList = localStorage.getItem("cast");
    console.log(ViewList)

    getMaxPageVal(ViewList);

    //글 리스트 정보 가져오기
    const getList = () => {
        console.log("getList()호출")
        if (ViewList != null) {
            if (ViewList === "home") { //홈
                var param = JSON.parse(localStorage.getItem("param"))
                myFetch.get(`/ajax/vehicle/homSearch`, data => {
                    makeRecordList(data);
                }, {
                    'manufacturer': param.manufacturer,
                    'model': param.model,
                    'detail_model': param.detail_model,
                    'currentPage': currentPage,
                    'recordCount': recordCount
                });

            } else if(ViewList === "modelSearch"){ //모델
                var param = JSON.parse(localStorage.getItem("param"))
                console.log(param.searchVal)
                myFetch.get(`/ajax/vehicle/search`, data => {
                    makeRecordList(data);
                },{
                    'searchVal' : param.searchVal,
                    'currentPage': currentPage,
                    'recordCount': recordCount,
                    'category': "수입",
                });

            }else if(ViewList === "sideSearch"){ //사이드 검색
                var param = JSON.parse(localStorage.getItem("param"))
                myFetch.get(`/ajax/vehicle/searchList`, data => {
                    makeRecordList(data);
                },{
                    'compunyResult' : param.compunyResult,
                    'Min_Mileage' : param.Min_Mileage,
                    'Max_Mileage' : param.Max_Mileage,
                    'Min_price' : param.Min_price,
                    'Max_price' : param.Max_price,
                    'gearboxResult' : param.gearboxResult,
                    'fuelResult' : param.fuelResult,
                    'ariaResult' : param.ariaResult,
                    'currentPage': currentPage,
                    'recordCount': recordCount,
                    'category': "수입",
                    'root': "sideSearch"
                });

            }else if(ViewList === "AllList"){ //수입
                myFetch.get(`/ajax/vehicle/forlist`, data => {
                    makeRecordList(data);
                }, {currentPage, recordCount});
            }
        }
    }


    const makePaging = () => {
        ulElem.innerHTML = null;
        const calcPage = parseInt((currentPage - 1) / pagingCount);
        const startPage = (calcPage * pagingCount) + 1;
        const lastPage = (calcPage + 1) * pagingCount;
        if(startPage > 1) {
            makePagingItem('&lt;', () => {
                currentPage = startPage - 1;
                localStorage.setItem("currentPage", currentPage);
                getList();
                makePaging();
            });
        }
        for(let i=startPage; i<=(lastPage > maxPage ? maxPage : lastPage); i++) {
            makePagingItem(i, () => {
                if(currentPage !== i) {
                    currentPage = i;
                    localStorage.setItem("currentPage", currentPage);
                    getList();
                }
            });
        }
        if(maxPage > lastPage) {
            makePagingItem('&gt;', () => {
                currentPage = lastPage + 1;
                localStorage.setItem("currentPage", currentPage);
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
        // console.log(list);
        if(listdivElem){
            listdivElem.innerHTML = '';
            list.forEach(item =>{
                const resultdiv = document.createElement('div');
                resultdiv.className = "col card_div";
                resultdiv.innerHTML = `
                    <div class="card shadow-sm bg-white h-100 " xmlns:c="http://www.w3.org/1999/html">
                        <input type="hidden" value="${item.selliboard}">
                        
                        <img class="card-img-top car_img h-50" src="/vehicleImg/${item.selliboard}/${item.mainimg}" alt="이미지없음">
                        <div class="card-body">
                            <h4 class="card-title">${item.detail_model}</h4>
                            <p class="card-text">${item.fuel}</p>
                            <p class="card-text">${item.price}만원</p>
                            <p class="card-text">판매지역: ${item.trading_area}</p>        
                            <p class="card-text">좋아요 수: ${item.likesum}</p>                   
                        </div>    
                    </div>
                `
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