<#macro page title js=[] css=[] keywords=""> 
<#include "WEB-INF/views/baseMacro.ftl">     
<@base base_title=title mainjs='/blog/js/main' base_css=css base_keywords="个人博客,Chu Lung">
<div class="container">
	<div class="blog-header">
		<h1 class="blog-title">Chu Lung's Blog</h1>
		<p class="lead blog-description">唯爱、技术和美食三者不可辜负.</p>
	</div>
	<div class="row">
		<div class="col-sm-8 blog-main">
		<#nested>
		</div>
		<div class="col-sm-3 col-sm-offset-1 blog-sidebar">
			<div class="sidebar-module sidebar-module-inset">
				<h4>Hi</h4>
				<p>欢迎来到我的博客。</p>
				<p>不会前端的后端工程师不是好的架构师。</p>
				<p>PHP是最好的语言。</p>
			</div>
			<#-- <div class="sidebar-module">
				<h4>标签</h4>
				<ol class="list-unstyled">
					<li><a href="#">March 2014</a></li>
				</ol>
			</div>
			-->
			<div class="sidebar-module">
				<h4>档案</h4>
				<ol id="articleFilings" class="list-unstyled">
				</ol>
			</div>
			<div class="sidebar-module">
				<h4>链接</h4>
				<ol class="list-unstyled">
					<li><a href="https://github.com/chulung" rel="external nofollow" target="_blank">GitHub</a></li>
				</ol>
			</div>
			<div class="sidebar-module">
				<h4>一起玩耍</h4>
				<ol class="list-unstyled">
					<li><a href="mailto:chulung@chulung.com" rel="external nofollow" target="_blank">邮箱</a></li>
				</ol>
			</div>
		</div>
	</div>
</div>
</@base>
</#macro>