<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>KityMinder Editor - Powered By FEX</title>
	<link href="favicon.ico" type="image/x-icon" rel="shortcut icon">
	<!-- bower:css -->
	<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" />
	<link rel="stylesheet" href="https://static.chulung.com/statics/markdown/lib/codemirror/codemirror.min.css" />
	<link rel="stylesheet" href="${staticsPath}/cmind/hotbox/hotbox.css" />
	<link rel="stylesheet" href="${staticsPath}/cmind/kityminder-core/dist/kityminder.core.css" />
	<link rel="stylesheet" href="${staticsPath}/cmind/color-picker/dist/color-picker.min.css" />
	<!-- endbower -->
	<link rel="stylesheet" href="${staticsPath}/cmind/kityminder.editor.min.css">

	<style>
		html, body {
			margin: 0;
			padding: 0;
			height: 100%;
			overflow: hidden;
		}
		h1.editor-title {
			background: #393F4F;
			color: white;
			margin: 0;
			height: 40px;
			font-size: 14px;
			line-height: 40px;
			font-family: 'Hiragino Sans GB', 'Arial', 'Microsoft Yahei';
			font-weight: normal;
			padding: 0 20px;
		}
		div.minder-editor-container {
			position: absolute;
			top: 40px;
			bottom: 0;
			left: 0;
			right: 0;
		}
	</style>
</head>
<body ng-app="kityminderDemo" ng-controller="MainController">
<h1 class="editor-title">KityMinder Editor - Powered By FEX</h1>
<kityminder-editor on-init="initEditor(editor, minder)"></kityminder-editor>
</body>

<!-- bower:js -->
<script src="https://apps.bdimg.com/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/angular.js/1.3.20/angular.min.js"></script>
<script src="https://cdn.bootcss.com/angular-ui-bootstrap/0.12.1/ui-bootstrap-tpls.js"></script>
<script src="${staticsPath}/markdown/lib/codemirror/lib/codemirror.js"></script>
<script src="${staticsPath}/markdown/lib/codemirror/mode/xml/xml.js"></script>
<script src="${staticsPath}/markdown/lib/codemirror/mode/javascript/javascript.js"></script>
<script src="${staticsPath}/markdown/lib/codemirror/mode/css/css.js"></script>
<script src="${staticsPath}/markdown/lib/codemirror/mode/htmlmixed/htmlmixed.js"></script>
<script src="${staticsPath}/markdown/lib/codemirror/mode/markdown/markdown.js"></script>
<script src="${staticsPath}/markdown/lib/codemirror/addon/mode/overlay.js"></script>
<script src="${staticsPath}/markdown/lib/codemirror/mode/gfm/gfm.js"></script>
<script src="${staticsPath}/cmind/angular-ui-codemirror/ui-codemirror.min.js"></script>
<script src="https://static.chulung.com/statics/markdown/lib/marked.min.js"></script>
<script src="${staticsPath}/cmind/kity/dist/kity.min.js"></script>
<script src="${staticsPath}/cmind/hotbox/hotbox.min.js"></script>
<script src="${staticsPath}/cmind/json-diff/json-diff.js"></script>
<script src="${staticsPath}/cmind/kityminder-core/dist/kityminder.core.min.js"></script>
<script src="${staticsPath}/cmind/color-picker/dist/color-picker.min.js"></script>
<!-- endbower -->

<script src="${staticsPath}/cmind/kityminder.editor.js"></script>


<script>
	angular.module('kityminderDemo', ['kityminderEditor'])
			.config(function (configProvider) {
				configProvider.set('imageUpload', '../server/imageUpload.php');
			})
			.controller('MainController', function($scope) {
				$scope.initEditor = function(editor, minder) {
					window.editor = editor;
					window.minder = minder;
				};
			});

</script>

</html>