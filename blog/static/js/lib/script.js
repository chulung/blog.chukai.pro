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
  /* wpadminbar */
  $(window).resize(function(){
    navbar_affix();
  })
} );
