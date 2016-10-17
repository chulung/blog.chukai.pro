<#list cikis?if_exists as ciki>
<div class="panel  panel-primary">
  <div class="panel-heading " id="${ciki.enIndex}">${ciki.title}</div>
  <div class="panel-body">
  <div class="list-group" >
   	<#assign enIndex=ciki.enIndex>
	<#list ciki.cikis as childciki>
		<a  class="list-group-item" href="/ciki/${enIndex}/${childciki.enIndex}">${childciki.title}</a>
	</#list>
  </div>
  </div>
</div>
</#list>