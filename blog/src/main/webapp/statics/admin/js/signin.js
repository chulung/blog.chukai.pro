$(function() {
	$('.val0or1').click(function() {
		$(this).val($(this).is(':checked') ? 1 : 0);
	});
});