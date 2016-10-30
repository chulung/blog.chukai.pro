<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="description" content="chulung's 的思维导图工具">
	<meta name="author" content="chulung">
	<meta content="在线思维导图,Online Mind Map" name="keywords">
	<title>cmind - chulung's craft</title>
	<link href="favicon.ico" type="image/x-icon" rel="shortcut icon">
	<!-- bower:css -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${assetsRoot}/markdown/lib/codemirror/codemirror.min.css" />
	<link rel="stylesheet" href="${assetsRoot}/cmind/hotbox/hotbox.css" />
	<link rel="stylesheet" href="${assetsRoot}/cmind/kityminder-core/dist/kityminder.core.css" />
	<link rel="stylesheet" href="${assetsRoot}/cmind/color-picker/dist/color-picker.min.css" />
	<!-- endbower -->
	<link rel="stylesheet" href="${assetsRoot}/cmind/kityminder.editor.min.css">
	<link rel="stylesheet" href="${assetsRoot}/cmind/cmind.css">
</head>
<body ng-app="cmind" ng-controller="MainController">
<div>
<h1 class="editor-title">
<a href="/">chulung</a><input class="title" type="text"/>
</h1> 

</div>
<kityminder-editor on-init="initEditor(editor, minder)"></kityminder-editor>
</body>

<!-- bower:js -->
<script src="https://apps.bdimg.com/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/angular.js/1.3.20/angular.min.js"></script>
<script src="https://cdn.bootcss.com/angular-ui-bootstrap/0.12.1/ui-bootstrap-tpls.js"></script>
<script src="${assetsRoot}markdown/lib/codemirror/lib/codemirror.js"></script>
<script src="${assetsRoot}markdown/lib/codemirror/mode/xml/xml.js"></script>
<script src="${assetsRoot}markdown/lib/codemirror/mode/javascript/javascript.js"></script>
<script src="${assetsRoot}markdown/lib/codemirror/mode/css/css.js"></script>
<script src="${assetsRoot}markdown/lib/codemirror/mode/htmlmixed/htmlmixed.js"></script>
<script src="${assetsRoot}markdown/lib/codemirror/mode/markdown/markdown.js"></script>
<script src="${assetsRoot}markdown/lib/codemirror/addon/mode/overlay.js"></script>
<script src="${assetsRoot}markdown/lib/codemirror/mode/gfm/gfm.js"></script>
<script src="${assetsRoot}cmind/angular-ui-codemirror/ui-codemirror.min.js"></script>
<script src="${assetsRoot}markdown/lib/marked.min.js"></script>
<script src="${assetsRoot}cmind/kity/dist/kity.min.js"></script>
<script src="${assetsRoot}cmind/hotbox/hotbox.min.js"></script>
<script src="${assetsRoot}cmind/json-diff/json-diff.js"></script>
<script src="${assetsRoot}cmind/kityminder-core/dist/kityminder.core.min.js"></script>
<script src="${assetsRoot}cmind/color-picker/dist/color-picker.min.js"></script>
<!-- endbower -->

<script src="${assetsRoot}cmind/kityminder.editor.js"></script>


<script>
	angular.module('cmind', ['kityminderEditor'])
			.config(function (configProvider) {
				configProvider.set('imageUpload', '/fileUpload/file');
			})
			.controller('MainController', function($scope) {
				$scope.initEditor = function(editor, minder) {
					window.editor = editor;
					window.minder = minder;
				};
			});

</script>

</html>