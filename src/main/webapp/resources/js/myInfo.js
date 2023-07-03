$(function () {

    // 사진변경 클릭
    $("#btn-image").click(function () {
        $("#img_file").click();
    });

    // 파일선택 변화감지
    $("#img_file").change(function () {
        const isConfirm = confirm("프로필 사진을 변경 하시겠습니까?");

        if (isConfirm) {
            // ajax 코드
            let formData = new FormData();
            const file = document.querySelector("#img_file").files[0];
            formData.append("file", file);
            $.ajax({
                url: "/change_profile",	//Controller 요청 주소
                type: "POST",
                enctype: 'multipart/form-data',
                data: formData,
                cache: false,
                contentType: false,
                processData: false,
                success: (data) => {
                    console.log(data);
                    if (data == 'true') {
                        alert("프로필사진 변경 완료");
                        location.href = '/myinfo';
                    } else {
                        alert("업데이트에 실패 했습니다.");
                    }
                },
                error: () => {
                    alert("오류가 발생 했습니다.");
                }
            });
        } else {
            // 선택 파일 비우기
            document.querySelector("#img_file").files = document.querySelector("#empty_file").files
        }
    });


    $('.infoEdit i').on('click', function () {
        var passwordInput = $(this).prev('input[name="password"]');
        passwordInput.toggleClass('active');
        if (passwordInput.hasClass('active')) {
            $(this).attr('class', "fa fa-eye-slash fa-lg");
            passwordInput.attr('type', 'text');
        } else {
            $(this).attr('class', "fa fa-eye fa-lg");
            passwordInput.attr('type', 'password');
        }
    });

    // 수정하기 버튼 클릭 이벤트
    $('#update').click(function (event) {
        //event.preventDefault(); // 폼의 기본 동작인 submit을 막음

        let userId = $('input[name="userId"]').val();
        let password = $('input[name="password"]').val();
        let email = $('input[name="email"]').val();
        let name = $('input[name="name"]').val();
        let nick = $('input[name="nick"]').val();
        let tel = $('input[name="tel"]').val();

        $.ajax({
            type: "POST", // 요청 method
            contentType: "application/json; charset=utf-8", // json 포맷 utf-8 내용으로 통신하겠다
            url: "/myinfo", // 어디 경로로 요청할 건지
            data: JSON.stringify({ // 객체를 -> JSON string 으로 변환
                userId: userId,
                password: password,
                email: email,
                name: name,
                nick: nick,
                tel: tel
            }), // 파라미터로 같이 담아서 보낼 것들
            success: function (data) {
                console.log(data);
                if (data === 'true') {
                    alert('수정완료');
                } else if (data === 'false') {
                    alert('수정실패');
                } else {
                    alert(data);
                }
                window.location.href = "/myinfo";
            }, // 요청에 대해 성공한 경우 수행할 내용
            error: function () {
                alert('실행 오류');
            } // 요청이 실패, 오류난 경우 수행할 내용
        });
    });

    // 탈퇴하기 버튼
    $("#dropout").click(function () {

        let userId = $('input[name="userId"]').val();

        if (confirm('정말 탈퇴 하시겠습니까?')) {
            $.ajax({
                type: "POST", // 요청 method
                contentType: "application/json; charset=utf-8", // json 포맷 utf-8 내용으로 통신하겠다
                url: "/drop", // 어디 경로로 요청할 건지
                success: function (data) {
                    console.log(data);
                    if (data === 'true') {
                        alert('회원탈퇴 완료');
                        location.href = "/home";
                    } else if (data === 'false') {
                        alert('이미 탈퇴한 회원입니다');
                        location.href = "/home";
                    }
                }, // 요청에 대해 성공한 경우 수행할 내용
                error: function () {
                    alert('오류가 발생했습니다');
                    location.href = "/myinfo";
                } // 요청이 실패, 오류난 경우 수행할 내용

            });
        }
    });


});