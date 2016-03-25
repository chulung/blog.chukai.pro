$(function() {
	$('#btn-save').click(function() {
		var title = $('#title').val();
		if ($.trim(title) == '') {
			alert('请输入标题');
			return;
		}
		var draftId = $('#editor-div').data('articledraftid');
		$.ajax({
			type : draftId ? "PUT" : "POST",
			url : "/articleDraft",
			data : {
				"id" : draftId,
				"title" : title,
				"typeId" : $('#typeId').val(),
				"context" : editor.getMarkdown(),
				"htmlContext" : editor.getHTML(),
				"isPublish" : $("#isPublish").is(":checked") ? 1 : 0,
				"version" : $('#editor-div').data('version')
			},
			dataType : "json",
			success : function(data) {
			}
		});
	});
})

function editInit() {
	var draftId = $('#editor-div').data('articledraftid');
	if (draftId) {
		$.ajax({
			type : "GET",
			url : "/articleDraft/" + draftId,
			data : {
				"version" : $('#editor-div').data('version')
			},
			dataType : "json",
			success : function(data) {
				editor.setMarkdown(data.context);
				$('#isPublish').val(data.isPublish);
				$('#title').val(data.title)
			}
		});
	}
}