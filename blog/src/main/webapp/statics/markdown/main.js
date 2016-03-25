var editor;
$(function() {
	editor = editormd("editor-div", {
		width : "90%",
		height : 740,
		path : baseStaticsPath+'/markdown/lib/',
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
		imageUpload : false,
		imageFormats : [ "jpg", "jpeg", "gif", "png", "bmp" ],
		imageUploadURL : "/dfs/file",
		toolbar : true,
		onload : function() {
		}
	});

});