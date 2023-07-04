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
        const idReg = /^[a-z][a-z0-9]{5,11}$/; // 6~12자리 소문자+숫자
        const pwReg = /^(?!((?:[A-Za-z]+)|(?:[~!@#$%^&*()_+=]+)|(?:[0-9]+))$)[A-Za-z\d~!@#$%^&*()_+=]{8,20}$/; // 8~20자리 영,특
        const emailReg = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i; // 이메일 abc@abc.abc 형태
        const nameReg = /[가-힣]{2,6}$/; // 이름 한글 2~6길이
        const nickReg = /[a-z|A-z|가-힣|0-9]{2,10}$/; // 닉네임 영,한,숫자 2~10자리
        const telReg = /^01([0|1|6|7|8|9]?)?([0-9]{3,4})?([0-9]{4})$/; // 0111234567, 01012345678 형태
        let isEmpty = false;
        let resultMsg = "";


        $("#form-signup input").each(function (index, item) {
            const thisValueLength = $.trim(item.value).length;

            if (thisValueLength === 0) {
                isEmpty = true;
                alert("빈 칸이 있습니다.");
                e.preventDefault();
                return false;
            }
        });

        const idValue = $("#input_id").val();
        const pwValue = $("#input_pw").val();
        const emailValue = $("#input_email").val();
        const nameValue = $("#input_name").val();
        const nickValue = $("#input_nick").val();
        const telValue = $("#input_tel").val();

        if (!idReg.test(idValue)) {
            resultMsg = "아이디: 6~12자의 영문 소문자, 숫자만 사용 가능합니다";
        } else if (!pwReg.test(pwValue)) {
            resultMsg = "비밀번호: 8~20자의 특수문자를 포함한 영문 대/소문자, 숫자를 사용해 주세요.";
        } else if (!emailReg.test(emailValue)) {
            resultMsg = "이메일: 이메일 형식과 맞지 않습니다";
        } else if (!nameReg.test(nameValue)) {
            resultMsg = "이름: 이름 형식과 맞지 않습니다";
        } else if (!nickReg.test(nickValue)) {
            resultMsg = "닉네임:  2~10자의 영문, 한글, 숫자만 사용 가능합니다";
        } else if (!telReg.test(telValue)) {
            resultMsg = "휴대폰번호: 번호 형식과 맞지 않습니다";
        }

        if (resultMsg != "") {
            alert(resultMsg);
            e.preventDefault();
            return false;
        }
    });

    // 아이디 입력창 포커스아웃 이벤트
    $("#input_id").focusout(function () {
        const idValue = $("#input_id").val();
        // 빈칸이면 api호출 안함
        if (idValue == "") {
            return false;
        } else {
            const idReg = /^[a-z][a-z0-9]{5,11}$/; // 6~12자리 소문자+숫자
            if (idReg.test(idValue)) {
                // 정규식 조건에 맞으면 중복체크 api 호출
                const userId = $(this).val();
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
            }
        }
    });

    // 전화번호 입력창 key up 이벤트
    $("#input_tel").keyup(function () {
        $("#input_tel").val($("#input_tel").val().replaceAll(/[^0-9]/g, ""));
    });

    $(".signin").click(function () {
		alert();

        const form = $("form")[0];
        const formData = new FormData(form);
        console.log(form);
        console.log(formData);

        $.ajax({
            cache : false,
            url : "/login", // 요기에
            processData: false,
            contentType: false,
            type : 'POST',
            data : formData,
            success : function(data) {
                console.log(data);
                if(data == true) {
                    location.href = "/";
                } else {
                    alert("정확한 회원정보를 입력해주세요");
                }
            }, // success
            error : function() {

            }
        });


    });

});