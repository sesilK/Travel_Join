$(function () {

    var sockJs = new SockJS('/ws');
    var stomp = Stomp.over(sockJs);

    var roomId = '';
    var username = $("body").data("username");

    var isScrolled = false;

    // 내 채팅방
    roomId = $('#chatbox').data("roomid");
    // console.log(roomId);

    stomp.connect({}, function () {
        // console.log("stomp 연결됨 2.12.6");

        stomp.subscribe("/sub/channel/" + roomId, function (chat) {
            console.log(chat);
            var message = JSON.parse(chat.body);
            var sender = message.sender;
            var content = message.content;
            var timeStamp = message.timeStamp;
            var chatId = message.chatId;
            var str = '';

            if (username === sender) {
                // str = "<div class='chat-box'>";
                // str += '<p> ' + username + ': ' + content + ' [' + timeStamp + ']</p>';
                // str += '</div>';
                str = "<div class='message right' data-chatid='" + chatId + "'>";
                str += "<img src='/profile/default_profile.png'/>";
                str += "<div class='bubble'>";
                str += content;
                str += "<span class='timestamp'>" + timeStamp + "</span>";
                str += "<span class='unread'>3</span>";
                str += "</div>";
                str += "</div>";


            } else {
                // str = "<div class='chat-box'>";
                // str += '<p> ' + content + '  [' + timeStamp + '] </p>';
                // str += '</div>';
                str = "<div class='message' data-chatid='" + chatId + "'>";
                str += "<span class='nick'>" + sender + "</span>";
                str += "<span class='timestamp'>" + timeStamp + "</span>";
                str += "<span class='unread'>3</span>";
                str += "<img src='/profile/default_profile.png'/>";
                str += "<div class='bubble'>";
                str += content;
                str += "</div>";
                str += "</div>";
            }

            $('#chat-messages').append(str); // 새로운 채팅 업데이트

            // 스크롤중이 아닐때, 새로운 채팅 생기면 스크롤 하단으로
            if (!isScrolled) {
                $("#chat-messages").animate({
                    scrollTop: $("#chat-messages").prop("scrollHeight") - 270
                }, 100);
            }

        });
    });


    // 채팅 전송버튼
    $('#send').click(function () {
        var content = $('#sendmessage input').val();
        if (content == '') {
            return;
        }

        stomp.send('/pub/send', {}, JSON.stringify({
            roomId: roomId,
            sender: username,
            content: content,
            type: "text"
        }));
        $('#sendmessage input').val('');

    });

    // 채팅 input창 엔터키 연결
    $('#sendmessage input').keypress(function (e) {
        if (e.which == 13) {
            $('#send').click();
        }
    });

    // 입력창 focus 이벤트 메세지 입력 문구 없애기
    $("#sendmessage input").focus(function () {
        if ($(this).val() == "메세지를 입력하세요") {
            $(this).val("");
        }
    });

    // 입력창 out focus 메세지 입력 문구 생성
    $("#sendmessage input").focusout(function () {
        if ($(this).val() == "") {
            $(this).val("메세지를 입력하세요");

        }
    });


    // 스크롤 이벤트
    $("#chat-messages").scroll(function () {
        // console.log($("#chat-messages").scrollTop() == ($("#chat-messages").prop("scrollHeight") - 270));

        const scrollHeight = $("#chat-messages").prop("scrollHeight") - 270;
        const scrollTop = $("#chat-messages").scrollTop();
        const scrollCal = scrollHeight - scrollTop;

        // 스크롤높이가 200보다 작으면 (최하단 근처)
        if (scrollCal < 300) {
            isScrolled = false;
            $("#scroll-btn").fadeOut(500);
            $(".scroll-btn").css({display: "none"});
        } else {    // 스크롤중일때
            isScrolled = true;
            $(".scroll-btn").fadeIn(500);
            $(".scroll-btn").css({display: "flex"});
        }
    });

    // 스크롤버튼 클릭
    $("#chat-messages").click(function () {
        if (isScrolled) {
            $("#chat-messages").animate({
                scrollTop: $("#chat-messages").prop("scrollHeight") - 270
            }, 100);
        }
    });

    $("#chat-messages").animate({
        scrollTop: $("#chat-messages").prop("scrollHeight") - 270
    }, 100);


});