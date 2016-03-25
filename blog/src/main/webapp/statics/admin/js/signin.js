$(function() {
//	$("#signin").click(function() {
//		reg = /^\w{5,17}$/
//		if (!reg.test($("input[name='userName']").val())) {
//			$("input[name='userName']").focus();
//			return;
//		}
//		if (!reg.test($("input[name='password']").val())) {
//			$("input[name='password']").focus();
//			return;
//		}
//		$.ajax({
//			type : "post",
//			url : "/admin/signIn",
//			data : $('form').serialize(),
//			dataType : "json",
//			success : function(data) {
//				if (data.success == 1) {
//					location.href = "/admin";
//				}
//			}
//		});
//	});
	$('.val0or1').click(function() {
		$(this).val($(this).is(':checked') ? 1 : 0);
	});
});