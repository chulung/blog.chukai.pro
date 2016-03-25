<#include "WEB-INF/views/baseMacro.ftl">     
<@base base_title="登陆" base_js=["/smelting/js/signIn.js"] base_css=[] base_keywords="熔炼,初的博客">
<div class="container">
  <div class="row">
	  <form id="sginInForm" action="/smelting/player/signIn" method="post">
				<div class="alert alert-info" role="alert">如果你没有注册，输入名字和口令将为你自动注册账号</div>
			  <div class="form-group">
			    <label for="inputUserName">姓名:</label>
			    <input name="userName" type="text" class="form-control" id="inputUserName" placeholder="你的名字" maxlength="10"  vlalue="${player.userName!''}/>
			  </div>
			  <div class="form-group">
			  </div>
			  <div class="form-group">
			    <label for="inputPassword">口令:<#if error><span class="label label-danger">(口令错误)</span></#if></label>
			    <input name="password" type="password" class="form-control" id="inputPassword" placeholder="5位数字口令" maxlength="5" vlalue="${player.password!''}"/>
			  </div>
		
			  <div class="form-group">
			  <button type="submit" class="btn btn-default">登录</button>
			  </div>
	</form>
  </div>
</div>
</@base>
