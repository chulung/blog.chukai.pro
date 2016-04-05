$(function() {

	$(".dropdown-toggle").on("click", function(e) {
		$('.dropdown-toggle').dropdown();
		$(".dropdown-menu").slideDown();
	});
	$('.dropdown-menu').mouseleave(function() {
		$(".dropdown-menu").slideUp();
	});
	$("html,body").on("click", function(e) {
		anp(e);
	});
	$(".nav-pills").children("li").on("click", function(e) {
		$(".nav-pills").children("li").removeClass("active");
		$(this).addClass("active");
	});
	$.ajax({
		url:"/tracker/userTracker",
		data:{"referer":document.referer,"href":window.location.href},
		type:"post"
		});
});
var chickCount = 0;
function anp(e) {
	var $i;
	var x = e.pageX, y = e.pageY;
	try {
		var obj = document.elementFromPoint(e.clientX, e.clientY);
		if ($(obj).html().length <= 20) {
			$i = $("<b>").text(
					"+" + (++chickCount) + " Hits! " + $(obj).html() + "!");
		} else {
			$i = $("<b>").text("+" + (++chickCount) + " Hits!");
		}
	} catch (e) {
		$i = $("<b>").text("+" + (++chickCount) + " Hits!");
	}
	$i.css({
		top : y - 20,
		left : x,
		position : "absolute",
		color : "#1692e4"
	});
	$("body").append($i);
	$i.animate({
		top : y - 180,
		opacity : 0,
		"font-size" : "1.4em"
	}, 1500, function() {
		$i.remove();
	});
	e.stopPropagation();
}
