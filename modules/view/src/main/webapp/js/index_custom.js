var bannerHeight = ($(window).height() - $('.header_wrapper').height());
var bannerWidth = ($(window).width());
$('.initial').css({
    'height': bannerHeight + 'px'
});

$('.logo').css({
    'max-height': bannerHeight * 0.6 + 'px',
    'max-width': bannerWidth * 0.6 + 'px',
    'margin-top': bannerHeight * 0.2 + 'px',
    'margin-bottom': bannerHeight * 0.1 + 'px'
});

$('.parallax-window-introduction').parallax({
    imageSrc: 'images/parallax.jpg'
});

$('#slideshow').carousel({
    interval: 15000
});

$(document).ready(function() {
    $(".navbar").on("click", "a", function(event) {
        //отменяем стандартную обработку нажатия по ссылке
        event.preventDefault();

        //забираем идентификатор бока с атрибута href
        var id = $(this).attr('href'),

            //узнаем высоту от начала страницы до блока на который ссылается якорь
            top = $(id).offset().top;

        //анимируем переход на расстояние - top за 1500 мс
        $('body,html').animate({
            scrollTop: top
        }, 1000);
    });
    $(".btn-intro-container").on("click", "a", function(event) {
        //отменяем стандартную обработку нажатия по ссылке
        event.preventDefault();

        //забираем идентификатор бока с атрибута href
        var id = $(this).attr('href'),

            //узнаем высоту от начала страницы до блока на который ссылается якорь
            top = $(id).offset().top;

        //анимируем переход на расстояние - top за 1500 мс
        $('body,html').animate({
            scrollTop: top
        }, 1000);
    });
});

$(document).ready(function() {
    $(window).scroll(function() {
        if ($(this).scrollTop() > 50) {
            $('#back-to-top').fadeIn();
        } else {
            $('#back-to-top').fadeOut();
        }
    });
    // scroll body to 0px on click
    $('#back-to-top').click(function() {
        $('#back-to-top').tooltip('hide');
        $('body,html').animate({
            scrollTop: 0
        }, 800);
        return false;
    });

    $('#back-to-top').tooltip('show');

    $(document).ready(function() {
        var show = true;
        var countbox = "#achievements";
        $(window).on("scroll load resize", function() {

            if (!show) return false; // Отменяем показ анимации, если она уже была выполнена

            var w_top = $(window).scrollTop(); // Количество пикселей на которое была прокручена страница
            var e_top = $(countbox).offset().top; // Расстояние от блока со счетчиками до верха всего документа

            var w_height = $(window).height(); // Высота окна браузера
            var d_height = $(document).height(); // Высота всего документа

            var e_height = $(countbox).outerHeight(); // Полная высота блока со счетчиками

            if (w_top + $(window).height() - 200 >= e_top || w_height + w_top == d_height || e_height + e_top < w_height) {

                $(".spincrement1").spincrement({
                    duration: 3000
                });
                $(".spincrement2").spincrement({
                    duration: 3000
                });
                $(".spincrement3").spincrement({
                    duration: 1500
                });

                show = false;
            }
        });
    });
}); 