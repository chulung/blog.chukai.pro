<#include "WEB-INF/views/pageMacro.ftl">     
<@page title=article.title >
	<div class="blog-post " data-id="${article.id}">
		<h3 class="blog-post-title"><span class="ico ico_type_${article.typeId}"></span>${article.title}<span id="endTime"><span></h3>
		<p class="blog-post-meta"><@DateTime time=article.createTime/>,${article.author}</p>
		<#if blog.derivationUrl?exists><p><a href="${article.derivationUrl}">原文链接</a></p></#if>
		<article>
		<p>作者：${article.author}</p>
		<p>原文链接:<a href="https://chulung.com/article/${article.id}">https://chulung.com/article/${article.id}</a></p>
		${article.context}
		<#if article.id==37>
			<style type="text/css">
			.heart {
			    background: rgba(0, 0, 0, 0) url("//static.chulung.com/statics/blog/images/web_heart_animation.png") no-repeat scroll left center / 2900% auto;
			    cursor: pointer;
			    height: 100px;
			    left: -14px;
			    position: absolute;
			    width: 100px;
			}
			.heart:hover, .heart:focus {
			    background-position: right center;
			}
			@keyframes heartBlast {
			0% {
			    background-position: left center;
			}
			100% {
			    background-position: right center;
			}
			}
			@keyframes heartBlast {
			0% {
			    background-position: left center;
			}
			100% {
			    background-position: right center;
			}
			}
			.heartAnimation {
			    animation-duration: 0.5s;
			    animation-iteration-count: 1;
			    animation-name: heartBlast;
			    animation-timing-function: steps(28);
			    background-position: right center;
			    display: inline-block;
			}
			.feed p {
			    font-family: "Microsoft YaHei","Georgia",Times,Times New Roman,serif;
			    font-size: 25px;
			}
			.feed {
			    clear: both;
			    height: 150px;
			    position: relative;
			}
			
			.likeCount {
			    color: #999999;
			    font-family: "Georgia",Times,Times New Roman,serif;
			    font-size: 25px;
			    margin-left: 68px;
			    margin-top: 32px;
			}
						
			</style>
			<div class="heart" id="like" rel="like"></div>
			<div class="likeCount" id="likeCount">&nbsp;</div>
			<h1>&nbsp;</h1>
			<p id="p1">文章后面似乎还有其他东西，是被粉红色的幕布所遮盖，幕布看起来可以移开的样子</p>
			<p id="p2" class="none" >你移开了幕布,幕布后显现出了一行文字，但其中两个关键数字看不清，似乎可以从文章推断出来<button id="btn-peek" type="button" class="btn btn-primary btn-xs">peek</button></p>
			<div>
			<p style="color: #FFB6C1;">点击██次，等待██秒!</p>
			<button id="btn-drag" type="button" class="btn btn-pink"></button>
			<p id="p-peek"></p>
			</div>
		</#if>
		</article>
	</div>
	<div class="panel panel-info">
	  <div class="panel-heading">查看评论</div>
	  <div class="panel-body comments-body ">
	    <ul  class="list-group">
		   <li class="list-group-item comments-li none">
		   	 <div class="list-group-item-heading">
			   	 <a name="" class="floor"></a>
			   	 <span class="date"></span>
			   	 <span class="name"></span>
			   	 <#--<div class="reply-div">
			   	 	<a>回复</a>
			   	 </div>-->
		   	 </div>
		     <div class="list-group-item-text"></div>
		   </li>
		</ul >
	  </div>
	</div>
		<div class="panel panel-primary" id="comments">
		  <div class="panel-heading">评论</div>
		  <div class="panel-body">
			  <form id="comments-form">
			  <input type="hidden" name="articleId" value="${article.id}">
			  <div class="form-group">
			    <label for="inputUserName">名字</label>
			    <input type="text" class="form-control" id="inputUserName" name="userName" maxlength="10" >
			  </div>
			  <div class="form-group">
			    <label for="inputEmail1">邮箱</label>
			    <input type="email" class="form-control" id="inputEmail1" name="email" placeholder="不公开，待本站邮件系统开通后用于回复提醒" maxlength="50" >
			  </div>
			  <div class="form-group">
			    <label for="inputComments">评论内容</label>
				  <textarea id="inputComments" class="form-control" rows="3" name="comment"  placeholder="不超过300字" maxlength="300"></textarea>
			  </div>
			  <button type="submit" class="btn btn-primary" id="submitComments">提交</button>
			</form>
	  </div>
	</div>
	<div data-spy="affix" data-offset-top="60" data-offset-bottom="200">
	</div>
</@page> 