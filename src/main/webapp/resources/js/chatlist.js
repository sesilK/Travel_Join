$(function () {

    var sockJs = new SockJS('/ws');
    var stomp = Stomp.over(sockJs);
    const myId = $("body").data("userid"); //세션값 가져옴

    stomp.connect({}, function () {

        // 내 채팅방 갯수만큼 반복
        $(".friend").each(function () {
            const roomId = ($(this).data("roomid"));
            const contentText = $(this).find(".last-chat");
            const thisList = $(this);

            stomp.subscribe("/sub/channel/" + roomId, function (chat) {

                var message = JSON.parse(chat.body);
                var sender = message.userId;
                var content = message.content;
                var timeStamp = message.time;
                var chatId = message.chatId;
                var unRead = message.unRead;
                var title = message.title;
                var chatListInfo = message.data;
                var type = message.type;

                if (type === 'text') {
                    contentText.text(sender + ": " + content);
                    thisList.prependTo("#friendslist"); // 리스트 맨 위로 이동

                    stomp.send('/pub/get_list', {}, JSON.stringify({
                        userId: myId,
                        planId: roomId,
                        type: 'info'
                    }));

                } else if (type === 'info') {
                    
                    // 채팅방 정보들 업데이트
                    for (let item of chatListInfo) {

                        const _planId = item.planId;
                        const _title = item.title;
                        const _imageId = item.imageId;
                        const _sender = item.sender;
                        const _content = item.content;
                        const _time = item.time;
                        const _chatCount = (item.chatCount == 0) ? "" : item.chatCount;

                        $('[data-roomid="' + _planId + '"] .chat-count').text(_sender + ": " + _content);
                        $('[data-roomid="' + _planId + '"] .chat-count').text(_time);
                        $('[data-roomid="' + _planId + '"] .chat-count').text(_chatCount);
                    }

                }


            });
        });


    });


    // 채팅방 클릭 이벤트
    $(".friend").click(function () {
        const roomId = $(this).data("roomid");
        location.href = '/chat/' + roomId;
    });

    // 페이지 나가면 out 신호 보내기
    $(window).on("beforeunload", function () {

    });
});

