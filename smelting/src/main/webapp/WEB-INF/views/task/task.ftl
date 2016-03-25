<#include "WEB-INF/views/baseMacro.ftl">     
<@base base_title="玩家信息" base_js=['/smelting/js/task.js'] base_css=[] base_keywords="熔炼,初的博客">
<div class="container">
  <div class="row">
	 <div class="panel panel-primary">
		  <!-- Default panel contents -->
		  <div class="panel-heading">任务信息</div>
		  
		<div class="panel-body">
		<#if task??>
		  <!-- Table -->
		  <table class="table">
			<tr>
			  <td >任务名</td>
			  <td id="taskName">${task.taskName}</td>
			</tr>
			<tr>
			  <td >任务描述</td>
			  <td >${task.taskDesc}</td>
			<tr>
			<tr>
			  <td >完成积分</td>
			  <td >${task.completePoint}</td>
			</tr>
			<tr>
			  <td >任务类型</td>
			  <td id="taskType"><#if task.taskType=1>团队<#else>个人</#if>任务</td>
			</tr>
			<tr>
			  <td >创建时间</td>
			  <td ><@DateTime time=task.createTime/></td>
			</tr>
			<tr>
			  <td >截止时间</td>
			  <td ><@DateTime time=task.endTime/></td>
			</tr>
		  </table>
		  </div>
		  <div class="panel-footer">
	        <button id="btn-receive" type="button" class="btn btn-success" data-taskid="${task.id}">领取</button>
		  </div>
		  </#if>
	 </div>
  </div>
</div>
</@base>
