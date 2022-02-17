{
    const homelogoElem = document.querySelector('#image_logo_home');

    if(homelogoElem){
        homelogoElem.addEventListener('click', ()=>{
           location.href = `/home`;
        });
    }
}