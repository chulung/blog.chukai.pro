<#compress>
<#macro base base_title mainjs="" base_css=[] base_keywords=""> 
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="ChuLung's craft">
<meta name="author" content="ChuLung">
<meta content="${base_keywords}" name="keywords">
<title>${base_title}-ChuLung's craft</title>
<link rel="shortcut icon" href="${staticsPath}/blog/images/favicon.ico"	type="image/x-icon" />
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet" href="${staticsPath}/blog/css/blog.css">
<#list base_css as c>
<link rel="stylesheet" href="${staticsPath}${c}">
</#list>
 <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body id="main-body" >
	<div class="nav-container container">
		<ul class="nav nav-pills">
	      <li <#if !typeId??> class="active" </#if> role="presentation"><a href="/">首页</a></li>
	      <li <#if typeId=1> class="active" </#if>  role="presentation"><a href="/articles">文章</a></li>
	      <li <#if typeId=3> class="active" </#if>  role="presentation"><a href="/reprints">他山之石</a></li>
	      <li <#if typeId=2> class="active" </#if>  role="presentation"><a href="/chatters">碎碎念</a></li>
	      <li <#if typeId=5> class="active" </#if>  role="presentation"><a href="/ciki/">Ciki</a></li>
	      <li <#if typeId=0> class="active" </#if>  role="presentation"><a href="/markdown">Markdown</a></li>
	      <li class="dropdown <#if typeId=4> active</#if>" role="presentation">
	       	<a aria-expanded="false" aria-haspopup="true" role="button" href="#" data-toggle="dropdown" class="dropdown-toggle">
	          	关于 <span class="caret"></span>
	        </a>
	        <ul class="dropdown-menu">
	          <li><a href="/about">ChuLung</a></li>
	          <li class="divider" role="separator"></li>
	          <li><a href="https://github.com/chulung/chulung.com" rel="external nofollow" target="_blank">本站源码</a></li>
	        </ul>
	      </li>
	    </ul>
	</div>
	<#nested>
	<footer class="blog-footer">
		<p>© 2015-2016 ChuLung</p>
		<p><a href="#">返回顶部</a></p>
		<p>湘ICP备15017845号</p>
		<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1260070301'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1260070301%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script>
	</footer>
</body>
<script>var staticsPath="${staticsPath}"</script>
<script>var module="${moduleName!''}"</script>
<script data-main='/statics${mainjs}.js' src="https://cdn.bootcss.com/require.js/2.2.0/require.min.js"></script>
<script>
(function(){
    var bp = document.createElement('script');
    var curProtocol = window.location.protocol.split(':')[0];
    if (curProtocol === 'https') {
        bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';        
    }
    else {
        bp.src = 'http://push.zhanzhang.baidu.com/push.js';
    }
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(bp, s);
})();
</script>
</html>
</#macro> 
</#compress>