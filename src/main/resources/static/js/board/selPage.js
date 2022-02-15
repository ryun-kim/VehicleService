
{

    const fm = document.querySelector('#in_form');
    var fileInfoArr=[];

    //차량이미지 클릭시 삭제.
    function fileRemove(index) {
        var imgId="#img_id_"+index;
        console.log("삭제할 번호"+index);
        delete fileInfoArr[index];
        $(imgId).remove(); //미리보기 사진 삭제

    }

    function check(file){ //차량 이미지 중복체크
        const arr =
            fileInfoArr.filter(
                (element) => true
            );

        for(var i=0; i<arr.length; i++){
            if(arr[i].name === file.name){
                return false;
            }
        }
        return true;
    }



    function filesList(){ //현재 전송될 사진목록
        const newArr =
            fileInfoArr.filter(
                (element) => true
            );
        return newArr.length;
        // console.log("전송될 파일목록")
        // console.log(newArr)
        // console.log("현재파일 배열상태")
        // console.log(fileInfoArr)
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
                if(filesList()==6){
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
        if (input.files && input.files[0]) {
            const reader = new FileReader();

            reader.onload = (e) => {
                const main_image = document.getElementById('main_image');
                const defult_img = document.getElementById('defult_img');
                main_image.style.width="200px";
                main_image.style.height="200px";
                main_image.style.margin="25px";
                main_image.style.display="block"
                main_image.src = e.target.result;
                defult_img.style.display="none"

            }
            reader.readAsDataURL(input.files[0]);
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

    function sub_fileBtn(){ //파일추가 버튼 클릭시 폴더열기
        document.querySelector('#sub_file').click();
    }

}

