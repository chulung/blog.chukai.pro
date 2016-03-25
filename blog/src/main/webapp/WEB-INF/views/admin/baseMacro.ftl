<#--最基本的后台模板宏，定义head 及css，js引入-->
<#compress>
<#macro page basejs=[] basecss=[]> 
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <title>初的博客 - 管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon"
	href="${staticsPath}/global/images/favicon.ico"
	type="image/x-icon" />
    <!-- Bootstrap -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="http://apps.bdimg.com/libs/jqueryui/1.9.2/themes/trontastic/jquery-ui.css" rel="stylesheet"/>
    
    <#--
    <link href="${staticsPath}/global/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${staticsPath}/global/css/jquery-ui.css" rel="stylesheet"/>
    -->
    
    <!-- styles -->
    <link href="${baseStaticsPath}/admin/css/styles.css" rel="stylesheet"/>
    <#list basecss as c>
	<link href="${staticsPath}${c}"  rel="stylesheet"/>
	</#list>
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
  </head>

				<#nested>
    <script>
    	var baseStaticsPath="${baseStaticsPath}";
    </script>				
    <script src="http://apps.bdimg.com/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="http://apps.bdimg.com/libs/jqueryui/1.9.2/jquery-ui.min.js"></script>
     <#--
    <script src="${staticsPath}/global/js/jquery.min.js"></script>
    <script src="${staticsPath}/global/js/bootstrap.min.js"></script>
    <script src="${staticsPath}/global/js/jquery-ui.min.js"></script>
     -->
    <script src="${baseStaticsPath}/admin/js/custom.js"></script>
<#list basejs as j>
<script src="${staticsPath}${j}"></script>
</#list>
</html>
</#macro> 
</#compress>