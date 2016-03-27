<#include "WEB-INF/views/pageMacro.ftl">     
<@page title=article.title js=["/blog/js/common.js"]>
	<div class="blog-post">
		<h3 class="blog-post-title"><span class="ico ico_type_${article.typeId}"></span>${article.title}</h3>
		<p class="blog-post-meta"><@DateTime time=article.createTime/>,${article.author},<#if isEdit><a href="/markdown/article/${article.id}">编辑</a></#if></p>
		<#if blog.derivationUrl?exists><p><a href="${blog.derivationUrl}">原文链接</a></p></#if>
		<article>${article.context}</article>
	</div>
</@page> 