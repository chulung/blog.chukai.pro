<#include "WEB-INF/views/baseMacro.ftl">     
<@base base_title="积分榜" base_js=[] base_css=[] base_keywords="熔炼,初的博客">
<div class="container">
	  <div class="row">
		  <div class="panel panel-success">
		      <div class="panel-heading">
		        <h3 class="panel-title"><span class="glyphicon glyphicon-king" aria-hidden="true"></span>顶级玩家</h3>
		      </div>
		      <div class="panel-body">
		         <#list players as player>
				   <div class="col-sm-6 col-md-4">
				    <div class="thumbnail">
				      <img src="..." alt="第${player_index+1}名">
				      <div class="caption">
				        <h3><a href="/smelting/player/${player.id}">${player.userName}</a></h3>
				        <p>总积分:${player.point}</p>
				      </div>
				    </div>
				  </div>
				  </#list>
		      </div>
	      </div>
	  </div>
		<div class="row">
		  <div class="panel panel-info">
		      <div class="panel-heading">
		        <h3 class="panel-title"><span class="glyphicon glyphicon-tower" aria-hidden="true">团队排名</h3>
		      </div>
		      <div class="panel-body">
		          <div id="myCarousel" class="carousel slide">
			      <!-- Carousel items -->
			      <div class="carousel-inner">
			      <#assign teamSize=teams?size>
			       <#list teams as team>
				        <div class="<#if team_index=0>active</#if> item">
							<table class="table table-hover">
								<thead>
									<th></th>
									<th></th>
									<th>战队/第${team_index+1}名</th>
									<th>玩家</th>
									<th>积分</th>
									<th></th>
									<th></th>
								</thead>
								<tbody>
									<#list team.players as player>
										<tr>
											<#if player_index=0>
											<td rowspan="${team.players?size}"></td>
											<td rowspan="${team.players?size}"></td>
											<td rowspan="${team.players?size}"> ${team.teamName}(${team.totalPoint})</td>
											</#if>
											<td>${player.userName}</td>
											<td>${player.point}</td>
											<#if player_index=0>
											<td rowspan="${team.players?size}"></td>
											<td rowspan="${team.players?size}"></td>
											</#if>
										</tr>
									</#list>
								</tbody>
							</table>
						</div>
					</#list>
			      </div>
			      <!-- Carousel nav -->
			      <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			      <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
			    </div>
		      </div>
	      </div>
		</div>
		
	  	<div class="row">
		  <div class="panel panel-warning">
		      <div class="panel-heading">
		        <h3 class="panel-title"><span class="glyphicon glyphicon-user" aria-hidden="true">个人排名</h3>
		      </div>
		      <div class="panel-body">
		     	 <table class="table table-hover">		</table>
		      </div>
	      </div>
		</div>
</div>
</@base>
