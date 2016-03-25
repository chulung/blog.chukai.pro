<#include "WEB-INF/views/baseMacro.ftl">     
<@base base_title="任务大厅" base_js=[] base_css=[] base_keywords="熔炼,初的博客">
<div class="container">
  <div class="row">
		<div class="alert alert-success" role="alert"><strong>最新任务</strong><a href="/smelting/task/new" class="btn btn-primary btn-lg active btn-xs">+新任务</a></div>
		<div class="row">
		<#list curTasks as task>
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <div class="caption">
		        <h3>${task.taskName}</h3>
		        <p>${task.taskDesc}</p>
				<p><span class="label label-info"><@DateTime time=task.endTime/>截止</span></p>
		        <p><a href="/smelting/task/${task.id}" class="btn btn-primary" role="button">查看</a></p>
		      </div>
		    </div>
		  </div>
		</#list>
	 	</div>
	  
		<div class="alert alert-warning" role="alert"><strong>已结束任务</strong></div>
		
		<div class="row">
		<#list expireTasks as task>
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <div class="caption">
		        <h3>${task.taskName}</h3>
		        <p>${task.taskDesc}</p>
				<p><span class="label label-warning"><@DateTime time=task.endTime/>截止(已结束)</span></p>
		        <p><a href="/smelting/task/${task.id}" class="btn btn-primary" role="button">查看</a></p>
		      </div>
		    </div>
		  </div>
		</#list>
	 	</div>
	 	
	</div>
  </div>
</@base>
