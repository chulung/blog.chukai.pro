<#include "WEB-INF/views/baseMacro.ftl">     
<@base base_title="战队信息" base_js=[] base_css=[] base_keywords="熔炼,初的博客">
<div class="container">
  <div class="row">
	 <div class="panel panel-primary">
		  <!-- Default panel contents -->
		  <div class="panel-heading"><#if isSelf>我<#else>${player.userName}</#if>的信息</div>
		
		  <!-- Table -->
		  <table class="table">
			<tr>
			  <td >姓名</td>
			  <td >${player.userName}</td>
			</tr>
			<tr>
			  <td >战队</td>
			  <td >
				<#if player.teamId!=0><a href="/smelting/team/${player.teamId}">${player.teamName}</a>
				<#else><a href="/smelting/team">
				<span class="label label-primary">加入战队</span></a></#if></td>
			<tr>
			</tr>
			  <td >身份</td>
			  <td >${player.position}</td>
			<tr>
			  <td >积分</td>
			  <td >${player.point}</td>
			</tr>
		  </table>
	 </div>
  </div>
</div>
</@base>
