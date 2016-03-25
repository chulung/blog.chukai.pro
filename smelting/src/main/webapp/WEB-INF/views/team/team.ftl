<#include "WEB-INF/views/baseMacro.ftl">     
<@base base_title="战队信息" base_js=['/smelting/js/team.js'] base_css=[] base_keywords="熔炼,初的博客">
<div class="container">
  <div class="row">
	 <div class="panel panel-primary">
		  <!-- Default panel contents -->
		  <div class="panel-heading">${team.teamName}的信息</div>
		  <!-- Table -->
		  <table class="table">
			<tr>
			  <td >总积分</td>
			  <td >${team.totalPoint}</td>
			</tr>
		  </table>
	 </div>
  </div>
  <div class="row">
	 <div class="panel panel-primary">
		  <!-- Default panel contents -->
		  <div class="panel-heading">玩家列表</div>
		  <!-- Table -->
		  <table class="table">
		 	<thead>
			  	<th>姓名</th>
			  	<th>积分</th>
		  	</thead>
		  	<tbody>
		  	<#if team?? && team.players??>
		  		<#list team.players as player>
		  		<tr>
		  			<td>${player.userName}</td>
		  			<td>${player.point}</td>
		  		</tr>
		  		</#list>
			</#if>
		  	</tbody>
		  </table>
	 </div>
  </div>
  <div class="row">
	 <div class="panel panel-primary">
		  <!-- Default panel contents -->
		  <div class="panel-heading">战队任务</div>
		  <!-- Table -->
		  <table class="table">
		 	<thead>
			  	<th>任务名</th>
			  	<th>任务类型</th>
			  	<th>领取人</th>
			  	<th>领取时间</th>
			  	<th>完成积分</th>
			  	<th>操作</th>
		  	</thead>
		  	<tbody>
		  	<#if team?? && team.taskRecives??>
		  		<#list team.taskRecives as taskRecive>
		  		<tr>
		  			<td>${taskRecive.userName}</td>
		  			<td>${taskRecive.taskType==1?string('团队任务','个人任务')}</td>
		  			<td>${taskRecive.userName}</td>
		  			<td><@DateTime time=taskRecive.createTime/></td>
		  			<td>${taskRecive.completePoint}</td>
		  			<td>${taskRecive.point}</td>
		  			<td>${taskRecive.point}</td>
		  			<td><#if taskRecive.giveOut==0><button class="btn btn-primary giveOut" data-toggle="modal" data-target=".bs-example-modal-sm" data-trid=${taskRecive.id}>分配积分</button></#if></td>
		  		</tr>
		  		</#list>
			</#if>
		  	</tbody>
		  </table>
	 </div>
  </div>
</div>
<!-- Small modal -->

<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
    	
    </div>
  </div>
</div>
</@base>
