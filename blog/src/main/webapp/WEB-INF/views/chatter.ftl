<#include "WEB-INF/views/pageMacro.ftl">     
<@page title="碎碎念" css=["/blog/css/timeline.css"]>
	<ul class="timeline">
	<#list chatterDtos as chatterDto>
    	<li><div class="tldate">${chatterDto.yearMonth}</div></li>
	    <#list chatterDto.chatters?if_exists as chatter>
	    <li ${(chatter_index%2==0)?string("","class='timeline-inverted'")} >
	      <div class="tl-circ"></div>
	      <div class="timeline-panel">
	        <div class="tl-heading">
	          <h4>Chu Lung</h4>
	          <p><small class="text-muted"><i class="glyphicon glyphicon-time"></i><@DateTime time=chatter.createTime/></small></p>
	        </div>
	        <div class="tl-body">
	          <p>${chatter.context}</p>
	        </div>
	      </div>
	    </li>
	    </#list>
    </#list>
  </ul>
</@page> 