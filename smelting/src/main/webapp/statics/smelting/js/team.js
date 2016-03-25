$(function() {
	$('.joinTeam').click(function() {
		var gn = $(this).parent().siblings(".name").html();
		if (gn) {
			var id = $(this).data('id');
			if (confirm("确认加入" + gn + "战队?此操作不可逆!")) {
				$.ajax({
					url : "/smelting/team/join",
					type : "post",
					dataType : "json",
					data : {
						'id' : id
					},
					success : function(data) {
						if (data.success == 1) {
							window.location.href = "/smelting/team/" + id;
						} else {
							alert(data.message)
						}
					}
				});
			}

		}
	});
	$('.giveOut').click(function(){
		$.ajax({
			url:"/smelting/taskRecive/"+$(this).data('trid'),
			type : "post",
			dataType : "json",
			success : function(data) {
				$('.modal-content').html(data);
			}
		});
	});
})