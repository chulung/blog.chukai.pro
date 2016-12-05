requirejs([ "jquery","bootstrap"], function($) {
	var exports={};
	var draftId = $('#btn-save').data('articledraftid');
	$('#btn-save').click(function() {
		var title = $('#title').val();
		if (!$.trim(title)) {
			alert('请输入标题');
			return;
		}
		$.ajax({
			type : draftId ? "PUT" : "POST",
			url : "/backend/articleDraft",
			data : {
				"id" : draftId,
				"title" : title,
				"typeId" : $('#typeId').val(),
				"content" : editor.getMarkdown(),
				"htmlContent":editor.getHTML(),
				"isPublish" : $("#isPublish").is(":checked") ? 'Y' : 'N',
				"pushBlog" :$("#pushBlog").is(":checked")?1:0,
				"license":$("#license").val()
			},
			dataType : "json",
			success : function(data) {
				if (data.success == 1) {
					alert('保存成功');
					$('#btn-save').data(data.id);
				}else{
					alert(data.message);
				}
			},
			error : function() {
				alert("服务器异常");
			}
		});
	});
	
	draftId && $.getJSON("/backend/articleDraft/"+draftId,function(rt){
		if (rt.result) {
			editor.setMarkdown(rt.result.content);
			$('#isPublish').prop("checked",
					rt.result.isPublish=='Y');
			$('#title').val(rt.result.title);
			$('#typeId').val(rt.result.typeId);
		}
	})
	return exports;
});