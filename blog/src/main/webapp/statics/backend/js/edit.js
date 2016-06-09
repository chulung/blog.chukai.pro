var editor;
$(function() {
	editInit();
	saveBtnInit();
})
var saving = false;
function saveBtnInit() {
	$('#btn-save')
			.click(
					function() {
						if (saving) {
							alert("正在保存中");
							return;
						}
						saving = true;
						var title = $('#title').val();
						if ($.trim(title) == '') {
							alert('请输入标题');
							return;
						}
						var draftId = $('#editor-div').data('articledraftid');
						$
								.ajax({
									type : draftId ? "PUT" : "POST",
									url : "/backend/articleDraft",
									data : {
										"id" : draftId,
										"title" : title,
										"typeId" : $('#typeId').val(),
										"context" : editor.getMarkdown(),
										"htmlContext" : editor.getHTML(),
										"isPublish" : $("#isPublish").is(
												":checked") ? "PUBLISHED"
												: "UNPUBLISHED"
									},
									dataType : "json",
									success : function(data) {
										if (data.success = 1) {
											alert('保存成功');
										}
									},
									error : function() {
										alert("服务器异常");
									},
									complete : function(jqXHR, textStatus) {
										saving = false;
									}
								});
					});
}
function editInit() {
	editor = editormd("editor-div", {
		width : "90%",
		height : 740,
		path : staticsPath + '/markdown/lib/',
		theme : "dark",
		previewTheme : "",// 预览主题
		editorTheme : "eclipse",// 代码主题
		markdown : "",
		codeFold : true,
		// syncScrolling : false,
		saveHTMLToTextarea : true, // 保存 HTML 到 Textarea
		searchReplace : true,
		// watch : false, // 关闭实时预览
		htmlDecode : "style,script,iframe|on*", // 开启 HTML 标签解析，为了安全性，默认不开启
		// toolbar : false, //关闭工具栏
		// previewCodeHighlight : false, // 关闭预览 HTML 的代码块高亮，默认开启
		emoji : true,
		taskList : true,
		tocm : true, // Using [TOCM]
		tex : false, // 开启科学公式TeX语言支持，默认关闭
		flowChart : false, // 开启流程图支持，默认关闭
		sequenceDiagram : false, // 开启时序/序列图支持，默认关闭,
		imageUpload : true,
		imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp" ],
		imageUploadURL : "/dfs/file",
		toolbar : true,
		onload : function() {
			var draftId = $('#editor-div').data('articledraftid');
			if (draftId) {
				$.ajax({
					type : "GET",
					url : "/backend/articleDraft/" + draftId,
					dataType : "json",
					success : function(data) {
						editor.setMarkdown(data.context);
						$('#isPublish').prop("checked",
								data.isPublish == 'PUBLISHED');
						$('#title').val(data.title)
						$('#articleType').val(data.typeId);
					}
				});
			} else {
				editor.setMarkdown(window.localStorage.autoSaveContext || "");
				$('#isPublish').prop("checked",
						!!window.localStorage.autoSaveIsPublish);
				$('#title').val(window.localStorage.autoSaveTitle || "");
				$('#articleType').val(
						window.localStorage.autoSaveArticleType || "1");
				setInterval(function() {
					window.localStorage.autoSaveContext = editor.getMarkdown();
					window.localStorage.autoSaveIsPublish = $("#isPublish").is(
							":checked") ? 1 : '';
					window.localStorage.autoSaveTitle = $('#title').val();
					window.localStorage.autoSaveArticleType = $('#articleType')
							.val();
					console.log("autoSave");
				}, 10000);
			}
		}
	});
}