{
    const searchFrmElem = document.querySelector('#search_Result_Frm');
    const jjimElemArr = document.querySelectorAll('.jjimBtn');
    const listorderElem = document.querySelector('#list_order');


    if(searchFrmElem){
        searchFrmElem.addEventListener('submit', ()=>{
            const searchVal = searchFrmElem.search_area.value;

            if(searchVal.length === 0){
                alert('검색어를 입력해 주세요');
            }

        });
    }

    if(jjimElemArr){
        jjimElemArr.forEach(item=>{
            const jjimHeart = item.firstChild;

            item.addEventListener('click', ()=>{
                if(jjimHeart.classList.contains('fa-solid')){
                    jjimHeart.classList.remove('fa-solid');
                    jjimHeart.classList.add('fa-regular');
                    item.classList.remove('btn-outline-danger');
                }else{
                    jjimHeart.classList.remove('fa-regular');
                    jjimHeart.classList.add('fa-solid');
                    item.classList.add('btn-outline-danger');
                }
            });
        })
    }

    if(listorderElem){


    };
}