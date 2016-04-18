<#include "WEB-INF/views/pageMacro.ftl">     
<@page title=article.title >
	<div class="blog-post ">
		<h3 class="blog-post-title"><span class="ico ico_type_${article.typeId}"></span>${article.title}</h3>
		<p class="blog-post-meta"><@DateTime time=article.createTime/>,${article.author}</p>
		<#if blog.derivationUrl?exists><p><a href="${article.derivationUrl}">原文链接</a></p></#if>
		<article>${article.context}</article>
	</div>
	<div class="panel panel-info">
	  <div class="panel-heading">查看评论</div>
	  <div class="panel-body">
	   <li></li>
	  </div>
	</div>
		<div class="panel panel-primary" id="comments">
		  <div class="panel-heading">评论</div>
		  <div class="panel-body">
			  <form id="comments-form">
			  <input type="hidden" name="articleId" value="${article.id}">
			  <div class="form-group">
			    <label for="inputUserName">名字</label>
			    <input type="text" class="form-control" id="inputUserName" name="userName" >
			  </div>
			  <div class="form-group">
			    <label for="inputEmail1">邮箱</label>
			    <input type="email" class="form-control" id="inputEmail1" name="email" placeholder="不公开，待本站邮件系统开通后用于回复提醒">
			  </div>
			  <div class="form-group">
			    <label for="inputComments">评论内容</label>
				  <textarea id="inputComments" class="form-control" rows="3" name="comment"  placeholder="不超过300字" maxlength="300"></textarea>
			  </div>
			  <button type="submit" class="btn btn-primary" id="submitComments">提交</button>
			</form>
	  </div>
	</div>
</@page> 