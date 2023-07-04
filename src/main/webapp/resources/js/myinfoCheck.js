$(function () {

    // 비밀번호 인풋창 엔터
    $('input[type=password]').keypress(function (e) {
        if (e.which == 13) {
            send();
        }
    });

    $(".signin").click(function () {
        send();
    });

    function send() {

        const form = $("form")[0];
        const formData = new FormData(form);
        // console.log(form);
        // console.log(formData);

        $.ajax({
            cache : false,
            url : "/myinfo/before", // 요기에
            processData: false,
            contentType: false,
            type : 'POST',
            data : formData,
            success : function(data) {
                // console.log(data);
                if(data == "성공") {
                    location.href = "/myinfo";
                } else {
                    alert(data);
                }
            }, // success
            error : function() {

            }
        });
    }
});