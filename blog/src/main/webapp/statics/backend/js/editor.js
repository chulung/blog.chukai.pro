//由于markdown配置路径有点不同，所以单独配置一份
requirejs
		.config({
			// github静态分离路径
			baseUrl : "https://static.chulung.com/statics/markdown/lib/",
			paths : {
				jquery : "https://apps.bdimg.com/libs/jquery/1.11.3/jquery.min",
				treeview : "https://cdn.bootcss.com/bootstrap-treeview/1.2.0/bootstrap-treeview.min",
				marked : "marked.min",
				prettify : "prettify.min",
				raphael : "raphael.min",
				underscore : "underscore.min",
				flowchart : "flowchart.min",
				jqueryflowchart : "jquery.flowchart.min",
				sequenceDiagram : "sequence-diagram.min",
				katex : "https://cdn.bootcss.com/KaTeX/0.1.1/katex.min",
				editormd : "../editormd.amd" // Using Editor.md amd version
			// for
			// Require.js
			},
			// 手动声明依赖部分
			shim : {
				jqueryflowchart : [ "jquery" ],
				treeview : [ "jquery" ],
				sequenceDiagram : [ "raphael" ]
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
		"../plugins/preformatted-text-dialog/preformatted-text-dialog",
		"treeview" ];
var editor;
require(deps, function(editormd) {
	editormd("editor-div", {
		width : "100%",
		height : 740,
		path : 'https://chulung.github.io/statics/markdown/lib/',
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
		imageUpload : false,
		imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp" ],
		imageUploadURL : "/dfs/file",
		toolbar : true,
		onload : function() {
			editor = this;
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
	});
	$.getJSON("/backend/category/list", function(rt) {
		if (rt.code = 1) {
			var exportTitel = function(nodes) {
				$.each(nodes, function() {
					this.text = "<span data-id='"
							+ this.id + "' data-type='" + this.type + "'>"
							+ this.text + "</span>";
					if (this.nodes) {
						exportTitel(this.nodes);
					}
				})
				return nodes;
			}
			$("#editor-tree").treeview({
				data : exportTitel(rt.result),
				onNodeSelected : function(event, node) {
					var type=$(node.text).data("type");
					console.log(type!="ARTICLE");
					if (type=="CIKI_CATE" || type=="BLOG_CATE") {
						$("#lb-cate").text("新建"+$($("#editor-tree").treeview("getParent",node).text).text()+"-"+$(node.text).text());
					}
					if (type=="ARTICLE" || type=="CIKI_TEXT") {
						$("#lb-cate").text("编辑"+$($("#editor-tree").treeview("getParent",node).text).text()+"-"+$(node.text).text());
						$.getJSON("/backend/articleDraft/"+$(node.text).data("id"), function(rt) {
							editor.setMarkdown(rt.context);
							$("#lb-cate").val($(node))
							$('#isPublish').prop("checked",
									rt.isPublish == 'PUBLISHED');
							$('#title').val(rt.title)
							$('#articleType').val(rt.typeId);
						});
					}
				}
			})
		}
	});
	var exports = {};
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
});
