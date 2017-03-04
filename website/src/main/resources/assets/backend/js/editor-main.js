//由于markdown配置路径有点不同，所以单独配置一份
requirejs
		.config({
			// 静态分离路径
			baseUrl : "https://chulung.github.io/assets/markdown/lib/",
			paths : {
				jquery : "https://apps.bdimg.com/libs/jquery/1.11.3/jquery.min",
				bootstrap : 'https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min',
				treeview : "https://cdn.bootcss.com/bootstrap-treeview/1.2.0/bootstrap-treeview.min",
				marked : "marked.min",
				prettify : "prettify.min",
				raphael : "raphael.min",
				underscore : "underscore.min",
				flowchart : "flowchart.min",
				jqueryflowchart : "jquery.flowchart.min",
				sequenceDiagram : "sequence-diagram.min",
				katex : "https://cdn.bootcss.com/KaTeX/0.1.1/katex.min",
				editormd : "../editormd.amd.min", // Using Editor.md amd version
				ciki : "../../backend/js/ciki",
				contextMenu : "https://cdn.bootcss.com/bootstrap-contextmenu/0.3.4/bootstrap-contextmenu"
			// for
			// Require.js
			},
			// 手动声明依赖部分
			shim : {
				bootstrap : [ "jquery" ],
				jqueryflowchart : [ "jquery" ],
				treeview : [ "jquery","bootstrap" ],
				sequenceDiagram : [ "raphael" ],
				contextMenu:["jquery","bootstrap"]
			},
			waitSeconds : 30
		});
var deps = [ "editormd", "../plugins/link-dialog/link-dialog",
		"../plugins/reference-link-dialog/reference-link-dialog",
		"../plugins/image-dialog/image-dialog",
		"../plugins/code-block-dialog/code-block-dialog",
		"../plugins/table-dialog/table-dialog",
		"../plugins/emoji-dialog/emoji-dialog",
		"../plugins/goto-line-dialog/goto-line-dialog",
		"../plugins/help-dialog/help-dialog",
		"../plugins/html-entities-dialog/html-entities-dialog",
		"../plugins/preformatted-text-dialog/preformatted-text-dialog" ];
require(deps, function(editormd) {
	var exports={};
	exports.init = function (editor) {

		var draftId = $('#btn-save').data('articledraftid');
		if (draftId){
			$.getJSON("/backend/articleDraft/" + draftId, function (rt) {
				if (rt.result) {
					editor.setMarkdown(rt.result.content);
					$('#isPublish').prop("checked",
						rt.result.isPublish == 'Y');
					$('#title').val(rt.result.title);
					$('#typeId').val(rt.result.typeId);
					$('#tags').val(rt.result.tags);
				}
			})
		}else {
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
		$('#btn-save').click(function () {
			var title = $('#title').val();
			if (!$.trim(title)) {
				alert('请输入标题');
				return;
			}
			var data2 = {
				"id": draftId,
				"title": title,
				"typeId": $('#typeId').val(),
				"content": editor.getMarkdown(),
				"htmlContent": editor.getHTML(),
				"isPublish": $("#isPublish").is(":checked") ? 'Y' : 'N',
				"pushBlog": $("#pushBlog").is(":checked") ? 1 : 0,
				"license": $("#license").val(),
				"tags": $('#tags').val()
			};
			$.ajax({
				type: draftId ? "PUT" : "POST",
				url: "/backend/articleDraft",
				data:  JSON.stringify(data2),
				dataType: "json",
				contentType :'application/json',
				success: function (data) {
					if (data.success == 1) {
						alert('保存成功');
						$('#btn-save').data(data.id);
					} else {
						alert(data.message);
					}
				},
				error: function () {
					alert("服务器异常");
				}
			});
		});
		var $tags = $('#tags');
		$tags.bind('input propertychange',function(){
			$tags.val($tags.val().replace('，|\s',','));
		});

	}
	editormd("editor-div", {
		width : "100%",
		height : 740,
		path : '/assets/markdown/lib/',
		theme : "dark",
		previewTheme : "",// 预览主题
		editorTheme : "eclipse",// 代码主题
		markdown : "",
		codeFold : true,
		// syncScrolling : false,
		saveHTMLToTextarea : true, // 保存 HTML 到 Textarea
		searchReplace : true,
		// watch : false, // 关闭实时预览
		htmlDecode : "style,script,iframe|on*", // 开启 HTML
		// 标签解析，为了安全性，默认不开启
		// toolbar : false, //关闭工具栏
		// previewCodeHighlight : false, // 关闭预览 HTML
		// 的代码块高亮，默认开启
		toolbarAutoFixed : false,// 工具栏浮动
		emoji : true,
		taskList : true,
		tocm : true, // Using [TOCM]
		tex : false, // 开启科学公式TeX语言支持，默认关闭
		flowChart : false, // 开启流程图支持，默认关闭
		sequenceDiagram : false, // 开启时序/序列图支持，默认关闭,
		imageUpload : true,
		imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp" ],
		imageUploadURL : "/fileUpload/file",
		toolbar : true,
		onload : function() {
			exports.init(this);
		}

	});
});
