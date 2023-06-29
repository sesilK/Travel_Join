$(function () {

    // sign in 클릭
    $(".sign-in").click(function () {
        $(".register").fadeOut(300, function () {
            $(".login").fadeIn(300);
        });
    });

    // sign up 클릭
    $(".sign-up").click(function () {
        $(".login").fadeOut(300, function () {
            $(".register").fadeIn(300);
        });
    });

    // 생년월일, 성별 select 박스 변화감지 (숨겨둔 input에 생년월일 조합 만들어주기)
    $(".sel-text").change(function () {
        const year = $("#year").val();
        const month = $("#month").val();
        const day = $("#day").val();
        $("#birth").val(year + '-' + month + '-' + day);

        // select 1개라도 체크 안 되어있으면 막기
        $(".sel-text").each(function (index, item) {
            const thisValue = $.trim(item.value)
            if (thisValue === "성별" || thisValue === "년도" || thisValue === "월" || thisValue === "일") {
                $("#birth").val("");
                return false;
            }
        });
    });

    // 회원가입 submit 이벤트 전처리
    $("#form-signup").submit(function (e) {
        const idReg = /^[a-z][a-z0-9]{6,12}$/; // 6~12자리 소문자+숫자
        const pwReg = /^(?=.*[a-zA-Z])((?=.*\d)|(?=.*\W)).{8,20}$/; // 8~20자리 영,특
        const emailReg = /^[a-zA-Z0-9]+@[0-9a-zA-Z]+\.[a-z]+$/; // 이메일 abc@abc.abc 형태
        const nameReg = /^[ㄱ-ㅎ|가-힣]{2,6}$/; // 이름 한글 2~6길이
        const nickReg = /^[0-9a-zA-Zㄱ-ㅎ가-힣]{2,10}$/; // 닉네임 영,한,숫자 2~10자리
        const telReg = /^\d{2,3}\d{3,4}\d{4}$/; // 011-123-4567, 010-1234-5678 형태
        let isEmpty = false;
        let resultMsg = "";

        $("#form-signup input").each(function (index, item) {
            const thisValueLength = $.trim(item.value).length;
            const thisValue = $(item).val();

            if (thisValueLength === 0) {
                isEmpty = true;
                e.preventDefault();
                return false;
            } else {
                // input 정규식
                if (!idReg.test(thisValue)) {
                    resultMsg = "아이디: 6~12자의 영문 소문자, 숫자만 사용 가능합니다.";
                } else if (!pwReg.test(thisValue)) {
                    resultMsg = "비밀번호: 8~20자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.";
                } else if (!emailReg.test(thisValue)) {
                    resultMsg = "이메일: 이메일 형식과 맞지 않습니다";
                } else if (!nameReg.test(thisValue)) {
                    resultMsg = "이름: 이름 형식과 맞지 않습니다.";
                } else if (!nickReg.test(thisValue)) {
                    resultMsg = "닉네임:  2~10자의 영문, 한글, 숫자만 사용 가능합니다";
                } else if (!telReg.test(thisValue)) {
                    resultMsg = "휴대폰번호: 번호 형식과 맞지 않습니다.";
                }
            }
        });
        if (isEmpty) {
            alert("빈 칸이 있습니다.");
            e.preventDefault();
        } else if (resultMsg != "") {
            alert(resultMsg);
            e.preventDefault();
        }

    });

    // 아이디 입력창 포커스아웃 이벤트
    $("#input_id").focusout(function () {
        // 빈칸이면 api호출 안함
        if ($("#input_id").val() == "") {
            return false;
        }

        const userId = $(this).val();
        console.log(userId);
        $.ajax({
            type: 'post',
            url: '/api/register?userId=' + userId,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                if (data == false) {
                    alert("사용 불가능한 아이디입니다.");
                    $("#input_id").val("");
                    $("#input_id").focus();
                }
            },
            error: function () {
                console.log("아이디 중복체크 요청 실패");
            }
        })
    });

});