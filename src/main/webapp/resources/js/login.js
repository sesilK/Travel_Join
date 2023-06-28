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

        $("#form-signup input").each(function (index, item) {
            console.log(item.value);
            const thisValue = $.trim(item.value).length;

            if (thisValue === 0) {
                alert("빈 칸이 있습니다.");
                e.preventDefault();
                return false;
            }

        });
    });

    // 아이디 입력창 포커스아웃 이벤트
    $("#input_id").focusout(function () {
        // 빈칸이면 api호출 안함
        if($("#input_id").val() == "") {
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
                if(data == false) {
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