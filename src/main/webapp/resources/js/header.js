$(function () {

    $(".card-body").hover(
        function () {
            $(this).find("img").addClass("animate__animated animate__flipOutY");
            $(this).find("img").removeClass("animate__flipInY");
            $(this).find(".content").stop().fadeIn(1000);
        },
        function () {
            $(this).find("img").removeClass("animate__flipOutY");
            $(this).find("img").addClass("animate__flipInY");
            $(this).find(".content").stop().fadeOut(500);
        }
    );


});