$(function () {

    // 채팅방 클릭 이벤트
    $(".friend").click(function () {
        const username = prompt('임시 닉네임 입력');
        const roomId = $(this).data("roomid");
        location.href = '/chat/' + roomId + "?username=" + username;
    });


// $(".friend").each(function () {
//
//     $(this).click(function () {
//         var childOffset = $(this).offset();
//         var parentOffset = $(this).parent().parent().offset();
//         var childTop = childOffset.top - parentOffset.top;
//         var clone = $(this).find('img').eq(0).clone();
//         var top = childTop + 12 + "px";
//
//
//
//
//         $(clone).css({'top': top}).addClass("floatingImg").appendTo("#chatbox");
//
//         setTimeout(function () {
//             $("#profile p").addClass("animate");
//             $("#profile").addClass("animate");
//         }, 100);
//         setTimeout(function () {
//             $("#chat-messages").addClass("animate");
//             $('.cx, .cy').addClass('s1');
//             setTimeout(function () {
//                 $('.cx, .cy').addClass('s2');
//             }, 100);
//             setTimeout(function () {
//                 $('.cx, .cy').addClass('s3');
//             }, 200);
//         }, 150);
//
//         $('.floatingImg').animate({
//             'width': "68px",
//             'left': '108px',
//             'top': '20px'
//         }, 200);
//
//         var name = $(this).find("p strong").html();
//         var email = $(this).find("p span").html();
//         $("#profile p").html(name);
//         $("#profile span").html(email);
//
//         $(".message").not(".right").find("img").attr("src", $(clone).attr("src"));
//         $('#friendslist').fadeOut();
//         $('#chatview').fadeIn();
//
//         var preloadbg = document.createElement("img");
//         preloadbg.src = "https://s3-us-west-2.amazonaws.com/s.cdpn.io/245657/timeline1.png";
//
//         $('#close').unbind("click").click(function () {
//             $("#chat-messages, #profile, #profile p").removeClass("animate");
//             $('.cx, .cy').removeClass("s1 s2 s3");
//             $('.floatingImg').animate({
//                 'width': "40px",
//                 'top': top,
//                 'left': '12px'
//             }, 200, function () {
//                 $('.floatingImg').remove()
//             });
//
//             setTimeout(function () {
//                 $('#chatview').fadeOut();
//                 $('#friendslist').fadeIn();
//             }, 50);
//         });
//     });
// });

// $(window).on("beforeunload", function () {
//     stomp.disconnect();
// });

});

