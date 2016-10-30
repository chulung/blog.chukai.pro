jQuery(document).ready( function( $ ) {
    "use strict";

    /* Navbar */
    function navbar_affix(){
        var navbar_height = $('#masthead').height();
        $('.site-header-affix-wrapper').css('min-height', navbar_height);
    }
    navbar_affix();

    $(window).scroll(function(){
        if( $(window).scrollTop() > 80){
            $('.site-header').removeClass('header-dark').addClass('navbar-fixed');
            navbar_affix();
        }else{
            $('.site-header').addClass('header-dark');
            $('.site-header').removeClass('navbar-fixed');
        }
    });

    if( $(window).scrollTop() > 80){
        $('.site-header').removeClass('header-dark').addClass('navbar-fixed');
    }

    /* carousel-slider */
    $('.carousel').carousel({
        interval: 6500,
        pause: 'hover',
    })

    /* wpadminbar */
    $(window).resize(function(){
        navbar_affix();
    })

    /* Masonry Blog Layout */
    var $container = $('.container-post');
    $container.imagesLoaded( function(){
        $container.masonry();
    });

   // Gallery with justified Gallery
    $(".gallery").justifiedGallery({
        rowHeight: 180,
        captions: true,
        lastRow: 'justify'
    });

    // Lightbox gallery with magnificPopup
    $('.tiled-gallery, .justified-gallery').each(function (i) {
        $(this).magnificPopup({
            delegate: 'a',
            type: 'image',
            gallery: {
                enabled: true
            }
        });
    });

    $(window).on('load',function(){

        /* Filtering Image */
        var $wrapper = $('.wrapper-portfolio');
        $wrapper.isotope({
            filter: '*',
            animationOptions: {
                duration: 750,
                easing: 'easeOutBounce',
                queue: false
            }
        });

        $('.portfolio-sort > li').on('click', function(){
            $('.portfolio-sort > li.active').removeClass('active');
            $(this).addClass('active');

            var selector = $(this).attr('data-filter');
            $wrapper.isotope({
                filter: selector,
                animationOptions: {
                    duration: 750,
                    easing: 'easeOutBounce',
                    queue: false
                }
            });
            
            setProjects();

            return false;
        });

        /* Set Column Portfolio */
        function splitColumns() {
            var winWidth = $(window).width(), columnNumb = 1;
            if (winWidth > 1200) {
                columnNumb = 4;
            } else if (winWidth > 992 && winWidth < 1200) {
                columnNumb = 4;
            } else if (winWidth > 768 && winWidth < 992) {
                columnNumb = 2;
            } else if (winWidth > 662 && winWidth < 768) {
                columnNumb = 2;
            } else if(winWidth < 662 || winWidth < 480) {
                columnNumb = 1;
            }
            return columnNumb;
        }

        function setColumns() {
            var winWidth = $(window).width(), columnNumb = splitColumns(), postWidth = Math.floor(winWidth / columnNumb);
            $wrapper.find('.wrapper-portfolio li').each(function () {
                $(this).css( {
                width : postWidth + 'px'
                });
            });
        }

        function setProjects() {
            setColumns();
            $wrapper.isotope('reLayout');
        }

        $wrapper.imagesLoaded(function () {
            setColumns();
        });

        $(window).bind('resize', function () {
            setProjects();
        });
    })
} );
