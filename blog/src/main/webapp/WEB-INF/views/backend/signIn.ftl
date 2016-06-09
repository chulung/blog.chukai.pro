<#--登陆页面只需引入基本宏-->
<#include "WEB-INF/views/backend/baseMacro.ftl">   
<@page basejs=["/backend/js/signin.js"] basecss=[]>

	<div class="page-content container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-wrapper">
			        <div class="box">
			            <div class="content-wrap">
			                <h6>Sign In</h6>
				                <form action="/backend/signIn" method="post">
				                <input class="form-control" type="text" name="userName" placeholder="User Name" value="${user.userName!''}">
				                <input class="form-control" type="password" name="password" placeholder="Password" value="${user.password!''}">
				                <div class="action">
				                    <input type="submit" value="Sign In"
				                     id="signin" class="btn btn-primary signup" />
				                </div>                
			            	</from>
			            </div>
			        </div>
			        <div class="already">
					    <p>© 2015-2016 初</p>
			        </div>
			    </div>
			</div>
		</div>
	</div>

</@page> 
