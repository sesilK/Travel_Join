$(document).ready(function () {

    var sockJs = new SockJS('/ws');
    var stomp = Stomp.over(sockJs);

    stomp.connect({}, function () {
        console.log("stomp 연결됨 2.12.6");
    });

    var type = 'send';
    var roomId = '';
    var username = prompt('임시 닉네임 입력');


    // 채팅 전송버튼
    $('#send').click(function () {
        var content = $('#sendmessage input').val();
        console.log(content);
        stomp.send('/pub/send', {}, JSON.stringify({
            roomId: roomId,
            sender: username,
            content: content,
            type: type
        }));
        $('#sendmessage input').val('');

    });

    // 채팅 input창 엔터키 연결
    $('#sendmessage input').keypress(function (e) {
        if (e.which == 13) {
            $('#send').click();
        }
    });

    // 입력창 포커스 메세지 입력 문구 없애기
    $("#sendmessage input").focus(function () {
        if ($(this).val() == "메세지를 입력하세요") {
            $(this).val("");
        }
    });

    // 입력창 포커스아웃 메세지 입력 문구 생성
    $("#sendmessage input").focusout(function () {
        if ($(this).val() == "") {
            $(this).val("메세지를 입력하세요");

        }
    });


    $(".friend").each(function () {

        // 내 채팅방 모두 연결
        roomId = $(this).data("roomid");
        console.log(roomId);

        stomp.subscribe("/sub/channel/" + roomId, function (chat) {
            console.log(chat);
            var message = JSON.parse(chat.body);
            var sender = message.sender;
            var content = message.content;
            var timeStamp = message.timeStamp;
            var str = '';

            if (username === sender) {
                str = "<div class='chat-box'>";
                str += '<p> ' + username + ': ' + content + ' [' + timeStamp + ']</p>';
                str += '</div>';
            } else {
                str = "<div class='chat-box'>";
                str += '<p> ' + content + '  [' + timeStamp + '] </p>';
                str += '</div>';
            }

            $('#chat-area').append(str); // 새로운 채팅 업데이트
            $("body").scrollTop($('#chat-area').height() - 100); // 채팅 오면 스크롤 맨 아래로
        });

        $(this).click(function () {
            var childOffset = $(this).offset();
            var parentOffset = $(this).parent().parent().offset();
            var childTop = childOffset.top - parentOffset.top;
            var clone = $(this).find('img').eq(0).clone();
            var top = childTop + 12 + "px";


            $(clone).css({'top': top}).addClass("floatingImg").appendTo("#chatbox");

            setTimeout(function () {
                $("#profile p").addClass("animate");
                $("#profile").addClass("animate");
            }, 100);
            setTimeout(function () {
                $("#chat-messages").addClass("animate");
                $('.cx, .cy').addClass('s1');
                setTimeout(function () {
                    $('.cx, .cy').addClass('s2');
                }, 100);
                setTimeout(function () {
                    $('.cx, .cy').addClass('s3');
                }, 200);
            }, 150);

            $('.floatingImg').animate({
                'width': "68px",
                'left': '108px',
                'top': '20px'
            }, 200);

            var name = $(this).find("p strong").html();
            var email = $(this).find("p span").html();
            $("#profile p").html(name);
            $("#profile span").html(email);

            $(".message").not(".right").find("img").attr("src", $(clone).attr("src"));
            $('#friendslist').fadeOut();
            $('#chatview').fadeIn();

            var preloadbg = document.createElement("img");
            preloadbg.src = "https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/timeline1.png";

            $('#close').unbind("click").click(function () {
                $("#chat-messages, #profile, #profile p").removeClass("animate");
                $('.cx, .cy').removeClass("s1 s2 s3");
                $('.floatingImg').animate({
                    'width': "40px",
                    'top': top,
                    'left': '12px'
                }, 200, function () {
                    $('.floatingImg').remove()
                });

                setTimeout(function () {
                    $('#chatview').fadeOut();
                    $('#friendslist').fadeIn();
                }, 50);
            });
        });
    });

    $(window).on("beforeunload", function () {
        stomp.disconnect();
    });
});