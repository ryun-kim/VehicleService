let kaobtn = document.getElementById('kaobtn');

Kakao.init('2f18039e9820e745b36d87a942a40fc7'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단

kaobtn.addEventListener('click', () => {
    Kakao.Auth.login({
        scope:'profile_nickname, profile_image, account_email, gender, birthday, age_range', //이범위내에서 정보를 가져온다
        success: function (response) { //로그인 성공시 callback함수 수행
            Kakao.API.request({
                url: '/v2/user/me', //이 url로부터 로그인한 사용자의 정보를 가져온다
                success: function (response) {
                    console.log(response) //이후 db에 이메일로 계정을 생성해주면된다
                },
                fail: function (error) {
                    console.log(error)
                },
            })
        },
        fail: function (error) {
            console.log(error)
        },
    })
});
