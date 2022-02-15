{
    const jjimElemArr = document.querySelectorAll('.jjimBtn');
    const jjimdel = document.querySelector('#jjim_Del_Btn');
    const titleElem = document.querySelector('#mypage_title');

    if(jjimElemArr){
        jjimElemArr.forEach(item=>{
            const jjimHeart = item.firstChild;

            if(jjimdel){
                jjimdel.addEventListener('click',()=>{
                    jjimHeart.classList.remove('fa-solid');
                    jjimHeart.classList.add('fa-regular');
                    item.classList.remove('btn-outline-danger');
                });
            }
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

    if(titleElem){
        titleElem.addEventListener('click', ()=>{
            location.href=`/mypage/userinfo`;
        });
    }
}