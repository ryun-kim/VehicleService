{
    const searchFrmElem = document.querySelector('#search_Result_Frm');
    const jjimElemArr = document.querySelectorAll('.jjimBtn');
    const listorderElem = document.querySelector('#list_order');
    const jjimdel = document.querySelector('#jjim_Del_Btn');

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


            jjimdel.addEventListener('click',()=>{
                jjimHeart.classList.remove('fa-solid');
                jjimHeart.classList.add('fa-regular');
                item.classList.remove('btn-outline-danger');
            });


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
        const listElem = listorderElem.querySelector('.nav-item');
        nav-item.forEach(item=>{
            item.addEventListener('click', ()=>{
                const activeElem = item.firstChild;``

                if(activeElem.classList.contains('active')){

                }
            })
        });
    };
}