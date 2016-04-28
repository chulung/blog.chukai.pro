$(function() {
	$('.btn-command').click(function() {
		if(!confirm("点击确定发送指令")){
			return;
		}
		$.ajax({
			url : '/smelting/command/execute/' + $(this).data('command'),
			dataType : 'json',
			type : "post",
			dataType : "json",
			success : function(data) {
				if (data.success != 1) {
					alert(data.message);
				}
			}
		});
	});
})