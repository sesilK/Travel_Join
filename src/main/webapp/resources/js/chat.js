$(function () {

    let sockJs = new SockJS('/ws');
    let stomp = Stomp.over(sockJs);
    stomp.debug = null;
    let subscriptions = [];

    const planId = $('#chatbox').data("roomid");
    const myId = $("#chatbox").data("userid"); //세션값 가져옴

    let isScrolled = false;


    stomp.connect({}, function () {

        // 채팅방 입장시 unRead 요청
        stomp.send('/pub/getUnread', {}, JSON.stringify({
            planId: planId
        }));


        let subscription = stomp.subscribe("/sub/channel/" + planId, function (chat) {
            let message = JSON.parse(chat.body);
            // console.log(message);
            let userId = message.userId;
            let content = message.content;
            let time = message.time;
            let chatId = message.chatId;
            let unRead = message.unRead;
            let type = message.type;
            let itemList = message.data;
            let str = '';


            // 받은 메세지가 text 타입이면
            if (type === 'text') {
                // 채팅 읽음처리 (본인포함)
                stomp.send('/pub/read', {}, JSON.stringify({
                    chatId: chatId,
                    planId: planId,
                    userId: myId,
                    type: 'read'
                }));

                // html 업데이트 부분
                if (myId === userId) {
                    str = "<div class='message right' data-chatid='" + chatId + "'>";
                    str += "<img src='/profile/default_profile.png'/>";
                    str += "<div class='bubble'>";
                    str += content;
                    str += "<span class='timestamp'>" + time + "</span>";
                    str += "<span class='unread' data-chatid='" + chatId + "'>" + unRead + "</span>";
                    str += "</div>";
                    str += "</div>";


                } else {
                    str = "<div class='message' data-chatid='" + chatId + "'>";
                    str += "<span class='nick'>" + userId + "</span>";
                    str += "<span class='timestamp'>" + time + "</span>";
                    str += "<span class='unread' data-chatid='" + chatId + "'>" + unRead + "</span>";
                    str += "<img src='/profile/default_profile.png'/>";
                    str += "<div class='bubble'>";
                    str += content;
                    str += "</div>";
                    str += "</div>";
                }
                $('#chat-messages').append(str); // 새로운 채팅 업데이트
                $("#chat-messages").scrollTop($("#chat-messages").prop("scrollHeight"));




                stomp.send('/pub/getUnread', {}, JSON.stringify({
                    planId: planId,
                    unRead: $("span.unread").length // 내 채팅창 채팅갯수
                }));

            } else if (type === 'unread') { // 받은 메세지가 unread 타입이면
                // 안 읽은숫자 업데이트
                for (let item of itemList) {
                    const chatId = item.chatId;
                    const unRead = item.unRead;


                    // unRead 가 0 이면 공백으로 치환
                    const temp_unRead = (unRead == 0) ? "" : unRead;
                    $('span.unread[data-chatid=' + chatId + ']').text(temp_unRead);
                }
            } else if (type === 'paging') {
                if (userId === myId && itemList.length > 0) {

                    let headChat = document.querySelectorAll(".message")[1];

                    for (let item of itemList) {


                        let _chatId = item.chatId;
                        let _userId = item.userId;
                        let _content = item.content;
                        let _time = item.time;
                        let _unRead = item.unRead;

                        if (_content != null && _content != NaN && _content != "") {
                            // console.log(_content + " ------------------------------------------");
                            if (myId === _userId) {
                                str = "<div class='message right'>";
                                str += "<img src='/profile/default_profile.png'/>";
                                str += "<div class='bubble'>";
                                str += _content;
                                str += "<span class='timestamp'>" + _time + "</span>";
                                str += "<span class='unread' data-chatid='" + _chatId + "'>" + _unRead + "</span>";
                                str += "</div>";
                                str += "</div>";


                            } else {
                                str = "<div class='message'>";
                                str += "<span class='nick'>" + _userId + "</span>";
                                str += "<span class='timestamp'>" + _time + "</span>";
                                str += "<span class='unread' data-chatid='" + _chatId + "'>" + _unRead + "</span>";
                                str += "<img src='/profile/default_profile.png'/>";
                                str += "<div class='bubble'>";
                                str += +_content;
                                str += "</div>";
                                str += "</div>";
                            }

                            $(".scroll-btn").after(str);
                            $("#chat-messages").scrollTop($("#chat-messages").prop("scrollHeight"));
                        }
                    }

                    stomp.send('/pub/getUnread', {}, JSON.stringify({
                        planId: planId,
                        unRead: $("span.unread").length // 내 채팅창 채팅갯수
                    }));

                }

            }


            // 스크롤중이 아닐때, 새로운 채팅 생기면 스크롤 하단으로
            if (!isScrolled) {
                $("#chat-messages").animate({
                    scrollTop: $("#chat-messages").prop("scrollHeight") - 270
                }, 100);
            }

            subscriptions.push(subscription);
        });
    });

    // 채팅 전송버튼
    $('#send').click(function () {
        let content = $('#sendmessage input').val();
        if (content == '' || content == '메세지를 입력하세요') {
            return;
        }

        // 채팅메세지 서버로 전송
        stomp.send('/pub/send', {}, JSON.stringify({
            planId: planId,
            userId: myId,
            content: content,
            type: "text"
        }));

        // $("#chat-messages").scrollTop 현재 스크롤 위치

        $('#sendmessage input').val('');
        // 스크롤 최하단 내리기
        document.querySelectorAll(".message")[document.querySelectorAll(".message").length - 1].scrollIntoView();
    });

    // 페이지 나가면 out 신호 보내기
    $(window).on("beforeunload", function () {
        // sendMessage('out', roomId, username); // out 신호 보내기
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

    // 스크롤바 백분율 소수점 둘째자리까지 구하는 함수
    function getScrollPositionPercentage(element) {
        let scrollableHeight = element.scrollHeight - element.clientHeight;
        let scrollPosition = element.scrollTop;
        let scrollPercentage = (scrollPosition / scrollableHeight) * 100;
        return scrollPercentage.toFixed(2); // 소수점 둘째 자리까지 반올림
    }


    // 스크롤 이벤트
    $("#chat-messages").scroll(function () {

        const scrollPer = getScrollPositionPercentage(this);

        if (scrollPer < 3.0) {
            // 제일 상단에 있는 chatId 보다 1 작은것
            const chatId = $(".unread")[0].dataset.chatid - 1;

            // 채팅메세지 서버로 전송
            stomp.send('/pub/chat_paging', {}, JSON.stringify({
                planId: planId, // 전역변수에서 가져옴
                chatId: chatId,
                userId: myId
            }));
        }

        // 스크롤높이가 바닥 근처에 있을때 (100.0 = 정확한 스크롤의 바닥지점)
        if (scrollPer > 97.0) {
            isScrolled = false;
            $("#scroll-btn").fadeOut(500);
            $(".scroll-btn").css({display: "none"});
        } else {    // 97.0 < 일때 스크롤중으로 판단
            isScrolled = true;
            $(".scroll-btn").fadeIn(500);
            $(".scroll-btn").css({display: "flex"});
        }
    });


    // 스크롤버튼 클릭
    $(".scroll-btn").click(function () {
        if (isScrolled) {
            $("#chat-messages").animate({
                scrollTop: $("#chat-messages").prop("scrollHeight")
            }, 100);
        }
    });


    // 페이지 나가면 out 신호 보내기
    $(window).on("beforeunload", function () {
        // 모든 구독 해제
        for (let i = 0; i < subscriptions.length; i++) {
            stomp.unsubscribe(subscriptions[i]);
        }

        subscriptions = []; // 배열 초기화
        stomp.disconnect();
    });

    // 입장시 스크롤 최하단 이동
    $("#chat-messages").scrollTop($("#chat-messages").prop("scrollHeight"));
});