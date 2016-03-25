<#--扩展基本宏，用于后台页面，定义顶部导航，及左侧菜单栏，-->
<#include "WEB-INF/views/admin/baseMacro.ftl">     
<#macro indexPage mianJs=[] miancss=[]> 
<@page basejs=mianJs basecss=miancss>
  <body>
  	<div class="header">
	     <div class="container">
	        <div class="row">
	           <div class="col-md-5">
	              <!-- Logo -->
	              <div class="logo">
	                 <h1><a href="/admin">初的博客 - 管理系统</a></h1>
	              </div>
	           </div>
	           <div class="col-md-5">
	              <div class="row">
	                <div class="col-lg-12">
	                  <div class="input-group form">
	                       <input type="text" class="form-control" placeholder="Search...">
	                       <span class="input-group-btn">
	                         <button class="btn btn-primary" type="button">Search</button>
	                       </span>
	                  </div>
	                </div>
	              </div>
	           </div>
	           <div class="col-md-2">
	              <div class="navbar navbar-inverse" role="banner">
	                  <nav class="collapse navbar-collapse bs-navbar-collapse navbar-right" role="navigation">
	                    <ul class="nav navbar-nav">
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">My Account <b class="caret"></b></a>
	                        <ul class="dropdown-menu animated fadeInUp">
	                          <li><a href="profile.html">Profile</a></li>
	                          <li><a href="/admin/logout">Logout</a></li>
	                        </ul>
	                      </li>
	                    </ul>
	                  </nav>
	              </div>
	           </div>
	        </div>
	     </div>
	</div>

    <div class="page-content">
    	<div class="row">
		  <div class="col-md-2">
		  	<div class="sidebar content-box" style="display: block;">
                <ul class="nav">
                    <!-- Main menu -->
                    <li class="current"><a href="/admin"><i class="glyphicon glyphicon-home"></i> Dashboard</a></li>
                    <li><a href="/admin/articles"><i class="glyphicon glyphicon-list"></i> 文章</a></li>
                    <li><a href="/admin/articleDrafts"><i class="glyphicon glyphicon-list"></i> 草稿</a></li>
                    <li><a href="/admin/editors"><i class="glyphicon glyphicon-pencil"></i> Markdown</a></li>
                    <li class="submenu">
                         <a href="#">
                            <i class="glyphicon glyphicon-list"></i> Pages
                            <span class="caret pull-right"></span>
                         </a>
                         <!-- Sub menu -->
                         <ul>
                            <li><a href="login.html">Login</a></li>
                            <li><a href="signup.html">Signup</a></li>
                        </ul>
                    </li>
                </ul>
             </div>
		  </div>
		  <div class="col-md-10">
		  	<#nested>
		  </div>
		</div>
    </div>
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
</@page>
</#macro> 
