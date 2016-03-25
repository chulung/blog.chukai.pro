<#include "WEB-INF/views/pageMacro.ftl">     
<@page title="首页" js=["/blog/js/common.js"]>
	<#list blogs as blog>
		<div class="blog-post">
			<h3 class="blog-post-title"><span class="ico ico_type_${blog.typeId}"></span><a href="/article/${blog.id}">${blog.title}</a></h3>
			<p class="blog-post-meta"><@DateTime time=blog.createTime/>,${blog.author}</p>
			<article>${blog.summary}</article>
			<#--
			<div>
			<a>评论(${blog.commentCount})</a>	<a>回复</a>
				<div>
				</div>
			</div>
			-->
		</div>
	</#list>
	<nav>
		<ul class="pager">
			<li><#if prePage?? ><a href="/blog/page/${prePage}?typeId=${typeId}">Previous</a></#if></li>
			<li><#if nextPage?? ><a href="/blog/page/${nextPage}?typeId=${typeId}">Next</a></#if></li>
		</ul>
	</nav>
</@page> 
