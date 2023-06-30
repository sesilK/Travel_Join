$(function () {

    var sockJs = new SockJS('/ws');
    var stomp = Stomp.over(sockJs);

    stomp.connect({}, function () {

        // 내 채팅방 갯수만큼 반복
        $(".friend").each(function () {
            const roomId = ($(this).data("roomid"));
            const contextArea = $(this).find(".last-chat");

            stomp.subscribe("/sub/channel/" + roomId, function (chat) {
                console.log(chat);
                var message = JSON.parse(chat.body);
                var sender = message.sender;
                var content = message.content;
                var timeStamp = message.timeStamp;
                var chatId = message.chatId;
                var unRead = message.unRead;
                var type = message.type;
                var title = message.title;

                if(type == 'text') {
                    contextArea.text(sender + ": " + content);
                }

                console.log(sender + ": " + content);
            });

            // stomp.connect({}, function () {
            //     stomp.subscribe("/sub/channel/" + roomId, function (chat) {
            //         console.log(chat);
            //         var message = JSON.parse(chat.body);
            //         var sender = message.sender;
            //         var content = message.content;
            //         var timeStamp = message.timeStamp;
            //         var chatId = message.chatId;
            //         var unRead = message.unRead;
            //         var str = '';
            //         var type = message.type;
            //
            //         // $(".last-chat").text(sender + ": " + content);
            //     });
            // });
        });


    });


    // 채팅방 클릭 이벤트
    $(".friend").click(function () {
        const roomId = $(this).data("roomid");
        location.href = '/chat/' + roomId;
    });

    // 페이지 나가면 out 신호 보내기
    $(window).on("beforeunload", function () {
        alert("out");
    });
});

