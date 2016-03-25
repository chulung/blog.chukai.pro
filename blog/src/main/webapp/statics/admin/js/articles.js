$(document).ready(function() {
	ajaxData(1);
});

function ajaxData(page) {
	$.ajax({
		url : '/articles',
		type : 'get',
		data : {
			'page' : page,
			'pageSize' : '10'
		},
		dataType : 'json',
		success : function(data) {
			$tbody = $('#articleTable').find('tbody');
			$(data).each(
					function(index, item) {
						$tr = $('<tr>').appendTo($tbody);
						$('<th>').html(item.id).appendTo($tr);
						$('<th>').html(item.title).appendTo($tr);
						$('<th>').html(item.author).appendTo($tr);
						$('<th>').html(item.createTime).appendTo($tr);
						$('<th>').html(item.updateTime).appendTo($tr);
						$('<th>').html(item.mender).appendTo($tr);
						$('<th>').html(item.version).appendTo($tr);
						$('<th>').append(
								$('<a>').html('编辑').attr(
										'href',
										'/admin/editors?articleId=' + item.id
												+ "&version=" + item.version))
								.appendTo($tr);
					});

		}
	});
}