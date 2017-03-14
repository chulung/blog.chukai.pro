/*
* Template Name: Unify - Responsive Bootstrap Template
* Author: @htmlstream
* Website: http://htmlstream.com
*/

var App = function() {
	// We extend jQuery by method hasAttr

  // Fixed Header
  function handleHeader() {
    jQuery(window).scroll(function() {
      if (jQuery(window).scrollTop() > 100) {
        jQuery('.header-fixed .header-sticky').addClass('header-fixed-shrink');
      } else {
        jQuery('.header-fixed .header-sticky').removeClass('header-fixed-shrink');
      }
    });
  }

  // More Articles
  function handleMoreArticles() {
    var is_box_visible = true;
    var distance_from_top = $('.outside-more-articles').attr('data-scrollTop');

	  $(window).scroll(function() {
	    if (($(window).scrollTop() > distance_from_top)&&(is_box_visible === true)) {
	      $('.outside-more-articles').addClass('outside-more-articles--show');
	    } else {
	      $('.outside-more-articles').removeClass('outside-more-articles--show');
	    }
    });
    $('.outside-more-articles__close').on('click', function(e) {
      $('.outside-more-articles').removeClass('outside-more-articles--show');
      is_box_visible = false;
    });
  }

  return {
    init: function() {
      handleHeader();
      handleMoreArticles();
    },
  };

}();
