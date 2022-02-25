
{

    const fm = document.querySelector('#in_form');
    var fileInfoArr=[];
    const pattern = /\d{2,3}[가-힣]{1}\d{4}$/; //차량번호 정규식

    //차량이미지 클릭시 삭제.
    function fileRemove(index) {
        var imgId="#img_id_"+index;
        delete fileInfoArr[index];
        $(imgId).remove(); //미리보기 사진 삭제
    }


    function filesList(){ //전송될 사진목록 공백제거하여 리턴
        const newArr =
            fileInfoArr.filter(
                (element) => true
            );
        return newArr;
    }

    //
    // function  test(fileNum){  다중파일 value값 변경할수있는 방법
    //      const dataTransfer = new DataTransfer();
    //         let files = $('#sub_file')[0].files;
    //         let fileArray = Array.from(files);
    //         fileArray.splice(fileNum, 1);
    //         fileArray.forEach(file => { dataTransfer.items.add(file); });
    //         $('#sub_file')[0].files = dataTransfer.files;
    //         console.log("파일삭제확인")
    //         console.log(targetObj.value)
    // }

    function check(file){ //차량 이미지 중복체크
        const arr = filesList();

        for(var i=0; i<arr.length; i++){
            if(arr[i].name === file.name){
                return false;
            }
        }
        return true;
    }


//차량이미지 추가 미리보기.
    function previewImage(targetObj, View_area) {
        var preview = document.getElementById(View_area); //이미지 컨테이너

        var files = targetObj.files;


        const div_style = 'display:inline-block;position:relative;'
            + 'width:100px;height:100px;margin:5px; z-index:1';

        const chk_style = 'width:30px;height:30px;position:absolute;font-size:24px;'
            + 'right:0px;bottom:0px;z-index:999;background-color:rgba(255,255,255,0.1);color:#f00';

        for ( var i = 0; i < files.length; i++) {

            var file = files[i];

            if(check(file)){ //차량중복체크
                if(filesList().length==6){
                    alert('사진은 최대 6장 까지 등록하실수있습니다.');
                    return;
                }
                fileInfoArr.push(file);

                var div = document.createElement('div')
                div.setAttribute('style', div_style)

                div.id="img_id_" +(fileInfoArr.length-1);
                var btn = document.createElement('input')
                btn.setAttribute('type', 'button')
                btn.setAttribute('value', 'x')
                btn.setAttribute('style', chk_style);
                btn.style.cursor='pointer';
                const idx=fileInfoArr.length-1
                btn.onclick=()=>fileRemove(idx);

                var img = document.createElement("img");
                img.style.width="100px";
                img.style.height="100px";
                img.className="addImg";
                img.classList.add("obj");
                img.file = file;


                div.appendChild(img)
                div.appendChild(btn)
                preview.appendChild(div);

                if (window.FileReader) { // FireFox, Chrome, Opera 확인.
                    var reader = new FileReader();
                    reader.onloadend = (function(aImg) {
                        return function(e) {
                            aImg.src = e.target.result;
                        };
                    })(img);
                    reader.readAsDataURL(file);
                }
            }

        }
    }




    function mainImg(input) {  //차량 대표사진
        if (input.files && input.files[0]) { //파일업로드 선택
            const main_image = document.getElementById('main_image');
            const reader = new FileReader();

            reader.onload = (e) => {

                main_image.style.width="200px";
                main_image.style.height="200px";
                main_image.style.margin="25px";
                main_image.style.opacity="1";
                main_image.src = e.target.result;


            }
            reader.readAsDataURL(input.files[0]);
        }else{  //파일업로드 취소
            main_image.src = '/img/main_car.png';
            main_image.style.opacity="0.3";
        }
    }

    var json=null ;
    var companyList ; // 제조사 리스트
    var modelList;   // 모델리스트
    var detailModelList; //세부모델리스트

    fetch("/json/MOCK_DATA.json") //json파일 가져오기
        .then(response => {
            return response.json();
        }).then(jsondata =>
        json = jsondata
    );



    function ko(index){
        companyList = json.result[index].companyList; //0 국산 1 수입 제조사리스트 저장
        var target = option_reset("sel_company");
        option_reset("sel_model");
        option_reset("sel_detailModel");
        for(var i=0; i<companyList.length; i++){ //제조사 옵션에 추가
            var opt = document.createElement("option");
            opt.value = companyList[i].company;

            opt.innerHTML = companyList[i].company;
            target.appendChild(opt);
        }
    }

    function addChange(e){
        var target = option_reset("sel_model");
        option_reset("sel_detailModel");
        for(var i=0; i<companyList.length; i++){
            if(e.value == companyList[i].company) {
                modelList= companyList[i].modelList;// 해당 제조사의 모델리스트 담기
            }
        }

        for(var i=0; i<modelList.length; i++){  //선택된 제조사의 모델 옵션에 담기
            var opt = document.createElement("option");
            opt.value = modelList[i].model;
            opt.innerHTML = modelList[i].model;
            target.appendChild(opt);
        }
        if(e.value==='제조사'){
            target.options.length = 1;
        }
    }

    function addChange2(e){
        var target = option_reset("sel_detailModel");
        for(var i=0; i<modelList.length; i++){
            if(e.value == modelList[i].model) {
                detailModelList= modelList[i].detailModelList; //선택된 모델의 세부모델 리스트 담기
            }
        }
        for(var i=0; i<detailModelList.length; i++){ //선택된 모델의 세부모델 옵션에 담기
            var opt = document.createElement("option");
            opt.value = detailModelList[i].detailModel;
            opt.innerHTML = detailModelList[i].detailModel;
            target.appendChild(opt);
        }
    }

    function option_reset(str){
        var target = document.getElementById(`${str}`)
        target.options.length = 1;
        return target;
    }


    const btnSubmit = document.querySelector('#btnSubmit');
    btnSubmit.addEventListener('click',input_check)//submit 클릭





    function input_check(){ //submit 클릭시 입력체크
        const car_number= fm.car_number
        const price= fm.price
        const street= fm.street
        const main_img=document.getElementById('file_upload').value;
        const detailModel = document.getElementById('sel_detailModel').value;
        var result= pattern.test(car_number.value);

        fetch(`/vehicle/carNumChk/${car_number.value}`)
            .then(res => res.json())
            .then((data) => {
                if(result===false || car_number.value==''){
                    alert("차량번호를 다시 확인 해 주십시오")
                    car_number.focus();
                }
                else if(data.result==1){
                    alert("이미 판매중인 차량번호입니다.")
                    car_number.focus();
                }else if(detailModel==='세부모델명'){
                    alert("등록할 차량모델을 선택해 주십시오")
                }else if(price.value==''){
                    alert("가격을 작성해 주십시오")
                    price.focus();
                }else if(street.value==''){
                    alert("주행거리를 작성해 주십시오")
                    street.focus();
                }else if(main_img==''){
                    alert("차량 대표사진을 설정해 주십시오")
                }else{

                    var checked_option = document.querySelector('#checked_option');
                    var subimg = document.querySelector('#subimg');
                    var explanations = document.querySelector('#explanations');
                    var options_length = document.getElementsByName('option').length;
                    var explanation = document.getElementsByName('explanation').length;
                    var options = [];
                    var explanaion_arr = [];
                    for(var i=0; i<options_length; i++){
                        let option = document.getElementsByName('option')[i];
                        if(option.checked == true){
                            options.push(option.value)
                        }
                    }
                    checked_option.value=options;
                    const dataTransfer = new DataTransfer();
                    filesList().forEach(file => { dataTransfer.items.add(file); });
                    subimg.files = dataTransfer.files;



                    for(var j=0; j<explanation; j++){
                        let expl = document.getElementsByName('explanation')[j].innerText;
                        explanaion_arr.push(expl)
                    }
                    explanations.value = explanaion_arr;

                    var none = document.querySelector('#none');
                    if(none.checked == true){
                        var input_color  = document.querySelector('.input_color');
                        none.value = input_color.value;
                    }
                     fm.submit();
                    }
            })


    }


    function sub_fileBtn(){ //파일추가시 폴더열기
        document.querySelector('#subimg').click();
    }


}