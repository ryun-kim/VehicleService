{
    const jjimElemArr = document.querySelectorAll('.jjimBtn');
    const jjimdel = document.querySelector('#jjim_Del_Btn');

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
}