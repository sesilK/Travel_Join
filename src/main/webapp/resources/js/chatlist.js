$(function () {

    let sockJs = new SockJS('/ws');
    let stomp = Stomp.over(sockJs);
    stomp.debug = null;
    const myId = $("#chatbox").data("userid"); //세션값 가져옴
    let subscriptions = [];

    stomp.connect({}, function () {

        // 내 채팅방 갯수만큼 반복
        $(".friend").each(function () {
            const roomId = ($(this).data("roomid"));
            const contentText = $(this).find(".last-chat");
            const thisList = $(this);

            let subscription = stomp.subscribe("/sub/channel/" + roomId, function (chat) {

                let message = JSON.parse(chat.body);
                let sender = message.userId;
                let content = message.content;
                let timeStamp = message.time;
                let chatId = message.chatId;
                let unRead = message.unRead;
                let title = message.title;
                let chatListInfo = message.data;
                let type = message.type;

                if (type === 'text') {
                    contentText.text(sender + ": " + content);
                    thisList.prependTo("#friendslist"); // 리스트 맨 위로 이동

                    stomp.send('/pub/get_list', {}, JSON.stringify({
                        userId: myId,
                        planId: roomId,
                        type: 'info'
                    }));

                } else if (type == 'info' && sender == myId) {

                    // 채팅방 정보들 업데이트
                    for (let item of chatListInfo) {

                        const _planId = item.planId;
                        const _title = item.title;
                        const _imageId = item.imageId;
                        const _sender = item.sender;
                        const _content = item.content;
                        const _time = item.time;
                        const _chatCount = (item.chatCount == 0) ? "" : item.chatCount;

                        $('.friend').each(function () {
                            const target_ele = $('.friend[data-roomid="' + _planId + '"]');
                            target_ele.find(".box-top .box-right span").text(_time);
                            target_ele.find(".box-bot .box-left span").text(_content);
                            target_ele.find(".box-bot .box-right span").text(_chatCount);
                        });

                        // $(".box-top .box-right span").text(_time); // 보낸시간
                        // $(".box-bot .box-left span").text(_content); // 채팅내용
                        // $(".box-bot .box-right span").text(_chatCount); // 채팅갯수
                    }

                }

                subscriptions.push(subscription);
            });
        });


    });


    // 채팅방 클릭 이벤트
    $(".friend").click(function () {
        const roomId = $(this).data("roomid");
        const chatId = $(this).find(".last-chat").data("chatid");
        location.href = '/chat/' + roomId;
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
});

