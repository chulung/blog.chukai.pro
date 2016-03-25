$(function() {
	$('#btn-receive').click(function() {
		var type = $('#taskType').html();
		if (type) {
			if (confirm("确认领取" + type + ":" + $("#taskName").val() + "?")) {
				$.ajax({
					url : "/smelting/task/receive",
					type : "post",
					dataType : "json",
					data : {
						'id' : $(this).data('taskid')
					},
					success : function(data) {
						if (data.success == 1) {
							window.location.reload();
						} else {
							alert(data.message)
						}
					}
				});
			}

		}
	});
})