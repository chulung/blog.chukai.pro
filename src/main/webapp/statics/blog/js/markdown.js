//由于markdown配置路径有点不同，所以单独配置一份
requirejs.config({
	//静态分离路径
	baseUrl : "https://static.chulung.com/statics/markdown/lib/",
	paths : {
		jquery : "https://apps.bdimg.com/libs/jquery/1.11.3/jquery.min",
		bootstrap : 'https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min',
		marked : "marked.min",
		prettify : "prettify.min",
		raphael : "raphael.min",
		underscore : "underscore.min",
		flowchart : "flowchart.min",
		jqueryflowchart : "jquery.flowchart.min",
		sequenceDiagram : "sequence-diagram.min",
		katex : "https://cdn.bootcss.com/KaTeX/0.1.1/katex.min",
		editormd : "../editormd.amd" // Using Editor.md amd version for
	// Require.js
	},
	//手动声明依赖部分
	shim : {
		jqueryflowchart : [ "flowchart" ],
		bootstrap : [ "jquery" ],
		jqueryflowchart : [ "jquery" ],
		sequenceDiagram : [ "raphael" ]
	},
	waitSeconds : 30
});

var deps = [ "../../blog/js/global" , "editormd","../plugins/link-dialog/link-dialog",
		"../plugins/reference-link-dialog/reference-link-dialog",
		"../plugins/image-dialog/image-dialog",
		"../plugins/code-block-dialog/code-block-dialog",
		"../plugins/table-dialog/table-dialog",
		"../plugins/emoji-dialog/emoji-dialog",
		"../plugins/goto-line-dialog/goto-line-dialog",
		"../plugins/help-dialog/help-dialog",
		"../plugins/html-entities-dialog/html-entities-dialog",
		"../plugins/preformatted-text-dialog/preformatted-text-dialog"];
var editor;
require(
		deps,
		function(global,editormd) {
			global.init();
			editormd(
					"editor-div",
					{
						width : "90%",
						height : 740,
						path : 'https://static.chulung.com/statics/markdown/lib/',
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
							// 尝试从localStorage取自动保存的值，没有则设定欢迎文描
							editor
									.setMarkdown(window.localStorage.autoSaveContext
											|| "#Hello World\n欢迎使用本站的markdown编辑器;<br>\n点击工具栏右上角的<i unselectable=\"on\" name=\"help\" class=\"fa fa-question-circle\"></i>查看语法和快捷键说明。<br>\n每10秒自动保存，基于html5的本地存储。<br>");
							//每隔10秒自动存储至本地
							setInterval(function() {
								window.localStorage.autoSaveContext = editor
										.getMarkdown();
								console.log("autoSave");
							}, 10000);
						}
					});

		});