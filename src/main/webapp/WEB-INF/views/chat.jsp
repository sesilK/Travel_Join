<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<body>
<c:set var="roomId" value="${room.roomId}"></c:set>
<div id="chat-area"></div>

<input id="input_send" type="text" placeholder="메세지를 입력하세요.">
<br>
<button id="btn_send" type="button">전송</button>

</body>
<script>
    $().ready(function () {

        var type = '';
        var roomId = '${roomId}';
        console.log(roomId);
        console.log('${roomId}');
        var username = prompt('이름?');

        var sockJs = new SockJS('/ws');
        var stomp = Stomp.over(sockJs);

        stomp.connect({}, function () {
            console.log("stomp 연결됨 2.12.6");

            stomp.subscribe("/sub/channel/" + roomId, function (chat) {
                console.log(chat);
                var message = JSON.parse(chat.body);
                var sender = message.sender;
                var content = message.content;
                var str = '';

                if (username === sender) {
                    str = "<div class='chat-box'>";
                    str += '<p>' + username + ': ' + content + '</p>';
                    str += '</div>';
                } else {
                    str = "<div class='chat-box'>";
                    str += '<p>' + sender + ': ' + content + '</p>';
                    str += '</div>';
                }

                $('#chat-area').append(str);
            });
        });

        $('#btn_send').click(function () {
            console.log('send: ' + $('#input_send').val());
            var content = $('#input_send').val();
            var sender = "쑤아릿";

            stomp.send('/pub/send', {}, JSON.stringify({roomId: roomId, sender: username, content: content, type: type}));
            $('#input_send').val('');
        });


    });
</script>
</html>
