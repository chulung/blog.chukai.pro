<#compress>
<#macro base base_title base_js=[] base_css=[] base_keywords=""> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="熔炼">
<meta name="author" content="初">
<meta content="${base_keywords}" name="keywords">
<title>熔炼果冻豆-${base_title}</title>
<link rel="shortcut icon" href="${staticsPath}/global/images/favicon.ico"	type="image/x-icon" />
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="//cdn.bootcss.com/formvalidation/0.6.1/css/formValidation.min.css" rel="stylesheet">
<#list base_css as c>
<link rel="stylesheet" href="${staticsPath}${c}">
</#list>
<link rel="stylesheet"
	href="${staticsPath}/smelting/css/smelting.css">
</head>
<body >
<div class="container">
  <div class="row">
   <ul role="tablist" class="nav nav-tabs">
      <li class="<#if pageName!='task'>no</#if>active" role="presentation"><a  href="/smelting/" ><span class="glyphicon glyphicon-tags" aria-hidden="true"></span>任务大厅</a></li>
      <li class="<#if pageName!='point'>no</#if>active" role="presentation" class=""><a href="/smelting/point" ><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span>积分榜</a></li>
      <li class="<#if pageName!='player'>no</#if>active" role="presentation" class=""><a href="/smelting/player" ><span class="glyphicon glyphicon-user" aria-hidden="true"></span>玩家</a></li>
      <li class="<#if pageName!='team'>no</#if>active" role="presentation" class=""><a href="/smelting/team" ><span class="glyphicon glyphicon-tower" aria-hidden="true"></span>战队</a></li>
      <li class="<#if pageName!='shop'>no</#if>active" role="presentation" class=""><a href="#" ><span class="glyphicon glyphicon-usd" aria-hidden="true"></span>积分商城</a></li>
    </ul>
  </div>
</div>
	<#nested>
</body>
<script>var staticsPath="${staticsPath}"</script>
<script>
	var _hmt = _hmt || [];
	(function() {
		var hm = document.createElement("script");
		hm.src = "//hm.baidu.com/hm.js?9c90c90a297f59a582c2f66fec1affa3";
		var s = document.getElementsByTagName("script")[0];
		s.parentNode.insertBefore(hm, s);
	})();
</script>
<script src="http://apps.bdimg.com/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/formvalidation/0.6.1/js/formValidation.min.js"></script>
<script src="//cdn.bootcss.com/formvalidation/0.6.1/js/framework/bootstrap.min.js"></script>
<#list base_js as j>
<script src="${staticsPath}${j}"></script>
</#list>
<script>
 $("#myCarousel").carousel('pause');
 </script>
 
</html>
</#macro> 
</#compress>