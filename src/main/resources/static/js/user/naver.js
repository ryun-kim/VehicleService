var naver_id_login = new naver_id_login("FKI5X4pkLPGeuMSpAt6W", "http://localhost:8090/user/login"); //callback url
var state = naver_id_login.getUniqState();
naver_id_login.setButton("green", 3, 88);
naver_id_login.setDomain("http://localhost:8090");
naver_id_login.setState(state);
naver_id_login.setPopup();
naver_id_login.init_naver_id_login();


// //메인홈페이지 구성되면 메인홈피에 작성
// var naver_id_login = new naver_id_login("FKI5X4pkLPGeuMSpAt6W", "http://127.0.0.1:5500/html/login.html");
// // 접근 토큰 값 출력
// alert(naver_id_login.oauthParams.access_token);
// // 네이버 사용자 프로필 조회
// naver_id_login.get_naver_userprofile("naverSignInCallback()");
// // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
// function naverSignInCallback() {
//     alert(naver_id_login.getProfileData('email'));
//     alert(naver_id_login.getProfileData('nickname'));
//     alert(naver_id_login.getProfileData('age'));
// }