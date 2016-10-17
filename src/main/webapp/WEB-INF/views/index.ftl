<#include "WEB-INF/views/pageMacro.ftl">     
<@page title="首页" >
	<#list blogs as blog>
		<div class="blog-post list_item">
			<h3 class="blog-post-title"><span class="ico ico_type_${blog.typeId}"></span><a href="/article/${blog.id}">${blog.title}</a></h3>
			<article class="summary">${blog.context}</article>
			<div class="article_manage">
		        <span class="link_postdate"><@DateTime time=blog.createTime/></span>
		        <span title="阅读次数" class="link_view"><a title="阅读次数" href="/article/${blog.id}">阅读</a>(${blog.visitCount})</span>
		        <span title="评论次数" class="link_comments"><a title="评论次数" href="article/${blog.id}#comments">评论</a>(${blog.commentCount})</span>
	    	</div>
		</div>
	</#list>
	<nav>
		<ul class="pager">
			<li><#if prePage?? ><a href="/blog/page/${prePage}?typeId=${typeId}">Previous</a></#if></li>
			<li><#if nextPage?? ><a href="/blog/page/${nextPage}?typeId=${typeId}">Next</a></#if></li>
		</ul>
	</nav>
</@page> 
