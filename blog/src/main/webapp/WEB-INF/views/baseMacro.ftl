<#compress>
<#macro base base_title base_js=[] base_css=[] base_keywords=""> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="初龙的博客">
<meta name="author" content="初">
<meta content="${base_keywords}" name="keywords">
<title>初的博客-${base_title}</title>
<link rel="shortcut icon" href="${staticsPath}/global/images/favicon.ico"	type="image/x-icon" />
<link rel="stylesheet"
	href="//cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
<#--
<link rel="stylesheet"
	href="${staticsPath}/global/css/bootstrap.min.css">
-->
<link rel="stylesheet" href="${staticsPath}/blog/css/blog.css">
<link rel="stylesheet" href="${staticsPath}/blog/css/global.css">
<#list base_css as c>
<link rel="stylesheet" href="${staticsPath}${c}">
</#list>
</head>
<body id="main-body" >
	<div class="container">
		<ul class="nav nav-pills">
	      <li <#if !typeId??> class="active" </#if> role="presentation"><a href="/">首页</a></li>
	      <li <#if typeId=1> class="active" </#if>  role="presentation"><a href="/articles.html">文章</a></li>
	      <li <#if typeId=2> class="active" </#if>  role="presentation"><a href="/essaies.html">随笔</a></li>
	      <li <#if typeId=3> class="active" </#if>  role="presentation"><a href="/reprint.html">他山之石</a></li>
	      <li <#if typeId=0> class="active" </#if>  role="presentation"><a href="/markdown">Markdown</a></li>
	      <li class="dropdown <#if typeId=4> active</#if>" role="presentation">
	       	<a aria-expanded="false" aria-haspopup="true" role="button" href="#" data-toggle="dropdown" class="dropdown-toggle">
	          	关于 <span class="caret"></span>
	        </a>
	        <ul class="dropdown-menu">
	          <li><a href="/about">初</a></li>
	          <li class="divider" role="separator"></li>
	          <li><a href="https://github.com/wenchukai/wenchukai.com" rel="external nofollow" target="_blank">本站源码</a></li>
	        </ul>
	      </li>
	    </ul>
	</div>
	<#nested>
	<footer class="blog-footer">
		<p>© 2015-2016 初</p>
		<p><a href="#">返回顶部</a></p>
		<p>湘ICP备15017845号</p>
	</footer>
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
<#--
<script src="${staticsPath}/global/js/jquery.min.js"></script>
<script src="${staticsPath}/global/js/bootstrap.min.js"></script>
-->
<script src="${staticsPath}/global/js/global.js"></script>
<#list base_js as j>
<script src="<#if !j?ends_with('?')>${staticsPath}<#else>/statics</#if>${j}"></script>
</#list>
</html>
</#macro> 
</#compress>