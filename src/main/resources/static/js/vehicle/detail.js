{

    $( document ).ready( function() {   //해당 위치로 스크롤 이동
        const header_bar = document.querySelector(".header_bar");
        const elMenus = header_bar.querySelectorAll("div");
        elMenus.forEach(function(elMenu) {
            elMenu.addEventListener("click", function() {

                const idx = Array.from(elMenus).indexOf(elMenu);

                let sx = document.querySelector(`.container>div:nth-child(${idx+1})`).offsetTop;
                document.querySelector('html').scroll({
                    left: 0,
                    top: sx-70,//해당위치높이값에서 헤더높이만큼 뺌
                    behavior: "smooth"
                });
            });
        });


        $('.slider-for').slick({  //슬라이드 쇼
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: true,
            fade: false,

            prevArrow: $('.prev'),
            nextArrow: $('.next')
        });
        $('.slider-nav').slick({
            slidesToShow: 6,
            slidesToScroll: 1,
            asNavFor: '.slider-for',
            focusOnSelect: true
        });
    } );



}