<#include "WEB-INF/views/baseMacro.ftl">     
<@base base_title="战队信息" base_js=['/smelting/js/team.js'] base_css=[] base_keywords="熔炼,初的博客">
<div class="container">
  <div class="row">
	 <div class="panel panel-primary">
	 	<div class="alert alert-info" role="alert">如果你没有战队，可以选择一个加入，或者，new一个！</div>
		  <!-- Default panel contents -->
		  <div class="panel-heading">战队列表</div>
		  		<div class="panel-body">
		  	<table class="table">
		  	<thead>
			  	<th>战队名</th>
			  	<th>总积分</th>
			  	<th>操作</th>
		  	</thead>
		  	<tbody>
		  		<#list teams as team>
		  		<tr>
		  			<td class="name">${team.teamName}</td>
		  			<td>${team.totalPoint}</td>
		  			<td><a href="/smelting/team/${team.id}">查看</a>
		  			<#if !hasTeam>
		  			<a href="javascript:;"class="label label-success joinTeam" data-id="${team.id}">加入</a></#if></td>
		  		</tr>
		  		</#list>
		  	</tbody>
		  </table>
		    </div>
		     <div class="panel-footer">
	        <button id="btn-receive" type="button" class="btn btn-info">new Team()</button>
		  </div>
	 </div>
  </div>
</div>
</@base>
