jQuery(document).ready( function( $ ) {
    "use strict";

    /* contact map */
    var map = $('.map');
    var lat = map.attr('data-lat');
    var lng = map.attr('data-lng');
    var title = map.attr('data-info-window-title');
    var content = map.attr('data-info-window-content');
    if( lat == '' ){ lat = -7.866315; }
    if( lng == '' ){ lng = 110.389574; }
    var position = {lat: +lat, lng: +lng};
    
    $('.map')
        .gmap3({
            zoom: 10,
            scrollwheel: false,
            center: position
        })
        .marker({
            position: position
        })
        .infowindow({
            content: title+content
        })
        .then(function (infowindow) {
            var map = this.get(0);
            var marker = this.get(1);
            marker.addListener('click', function() {
                infowindow.open(map, marker);
            });
        });
} );