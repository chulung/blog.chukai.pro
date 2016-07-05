<#--扩展基本宏，用于后台页面，定义顶部导航，及左侧菜单栏，-->
<#include "WEB-INF/views/backend/baseMacro.ftl">     
<#macro page mainJs miancss=[]> 
<@base baseJs=mainJs basecss=miancss>
  <body>
	<nav class="navbar navbar-default">
	  <div class="container-fluid">
	    <!-- Brand and toggle get grouped for better mobile display -->
	    <div class="navbar-header">
	      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
	      <a class="navbar-brand" href="/backend">HOME</a>
	    </div>
	
	    <!-- Collect the nav links, forms, and other content for toggling -->
	    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
	      <ul class="nav navbar-nav">
	         <li><a href="/backend/articleDrafts"><i class="glyphicon glyphicon-list"></i>草稿</a></li>
             <li><a href="/backend/cikiEditor"><i class="glyphicon glyphicon-pencil"></i>Ciki</a></li>
	      </ul>
	      <form class="navbar-form navbar-right" role="search">
	        <div class="form-group">
	          <input type="text" class="form-control" placeholder="Search">
	        </div>
	        <button type="submit" class="btn btn-default">Submit</button>
	      </form>
	    </div><!-- /.navbar-collapse -->
	  </div><!-- /.container-fluid -->
	</nav>
	  	<#nested>
    <footer>
         <div class="container">
         
            <div class="copy text-center">
               	<p>© 2015 初</p>
				<p><a href="#">返回顶部</a></p>
				<p>湘ICP备15017845号</p>
            </div>
            
         </div>
      </footer>
  </body>
</@base>
</#macro> 
