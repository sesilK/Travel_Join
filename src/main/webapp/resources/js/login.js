$(function () {

    // sign in 클릭
    $(".sign-in").click(function () {
        $(".register").fadeOut(300, function () {
            $(".login").fadeIn(300);
        });
    });

    // sign up 클릭
    $(".sign-up").click(function () {
        $(".login").fadeOut(300, function () {
            $(".register").fadeIn(300);
        });
    });
});