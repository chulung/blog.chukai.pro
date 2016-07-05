<#include "pageMacro.ftl">  
<@page>
		<div class="page-header"><h2>Ciki · Chu Lung's wiki</h2>
		</div>
		<#list cikis?if_exists as ciki>
		<div class="panel  panel-primary">
		  <div class="panel-heading " id="${ciki.enIndex}">${ciki.title}</div>
		  <div class="panel-body">
		  <div class="list-group" >
		   	<#assign enIndex=ciki.enIndex>
			<#list ciki.cikis as childciki>
				<a  class="list-group-item" href="https://ciki.chulung.com/${enIndex}/${childciki.enIndex}.html">${childciki.title}</a>
			</#list>
		  </div>
		  </div>
		</div>
		</#list>
</@page>
