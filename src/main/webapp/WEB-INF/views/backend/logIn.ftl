<#--登陆页面只需引入基本宏-->
<#include "WEB-INF/views/backend/baseMacro.ftl">   
<@base  basecss=[]>
   <div class="container">
      <form action="/backend/logIn" method="post">
        <h2 >Chu Lung's Blog</h2>
        <label for="inputUserName" class="sr-only">用户名</label>
        <input type="text" id="inputUserName" name="userName" class="form-control" placeholder="用户名" required autofocus>
        <label for="inputPassword" class="sr-only">密码</label>
        <input type="password" id="inputPassword" name="password"  class="form-control" placeholder="密码" required >
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      </form>
    </div> <!-- /container -->
</@base> 
