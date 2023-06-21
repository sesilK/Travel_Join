<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
<body>

<div id="chat-area"></div>

<input id="input_send" type="text" placeholder="메세지를 입력하세요.">
<br>
<button id="btn_send" type="button">전송</button>

</body>
<script>
    $().ready(function () {

        var type = '';
        var channelId = '';
        var username = prompt('이름?');

        channelId = prompt('채널아이디?');

        var sockJs = new SockJS('/ws');
        var stomp = Stomp.over(sockJs);

        stomp.connect({}, function () {
            console.log("stomp 연결됨 2.12.6");

            stomp.subscribe("/sub/channel/" + channelId, function (chat) {
                var content = JSON.parse(chat.body);
                var sender = content.sender;
                var message = content.data;
                var str = '';

                if (username === sender) {
                    str = "<div class='chat-box'>";
                    str += '<p>' + username + ': ' + message + '</p>';
                    str += '</div>';
                } else {
                    str = "<div class='chat-box'>";
                    str += '<p>' + sender + ': ' + message + '</p>';
                    str += '</div>';
                }

                $('#chat-area').append(str);
            });
        });

        $('#btn_send').click(function () {
            console.log('send: ' + $('#input_send').val());
            var data = $('#input_send').val();
            stomp.send('/pub/hello', {}, JSON.stringify({type: type, sender: username, channelId: channelId, data: data}));
            $('#input_send').val('');
        });


    });
</script>
</html>
