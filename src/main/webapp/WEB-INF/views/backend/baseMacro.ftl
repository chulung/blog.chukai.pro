<#--最基本的后台模板宏，定义head 及css，js引入-->
<#compress>
<#macro base baseJs basecss=[]> 
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <title>Backend-chulung's craft</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon"	href="/assets/craft/img/favicon.ico"	type="image/x-icon" />
    <!-- Bootstrap -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/assets/backend/css/backend.css" rel="stylesheet"/>
    <#list basecss as c>
	<link href="${assetsRoot}${c}"  rel="stylesheet"/>
	</#list>
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

				<#nested>
	<script>var assetsRoot="${assetsRoot}"</script>
	<script>var module="/assets/backend/js/${moduleName!''}.js"</script>
	<script data-main='/assets/backend/js/${baseJs}.js' src="https://cdn.bootcss.com/require.js/2.2.0/require.min.js"></script>
</html>
</#macro> 
</#compress>