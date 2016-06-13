<#include "WEB-INF/views/backend/pageMacro.ftl">     
<@page mianJs=["/markdown/editormd.js","/backend/js/edit.js"] miancss=["/markdown/css/editormd.css"]>
<div class="panel panel-default">
			<div class="panel-heading">
			  	  <div class="panel-title">
			      		<lable>标题<lable>
			      		<input id="title" class="title" type="text"/> 
			      		<input id="isPublish" type="checkBox" checked>发布</input>
						<select id="articleType">
						<#list articleTypes as type>
						<option value="${type.dictValue}">${type.dictDesc}</option>
						</#list>
						</select>
						<button id="btn-save">保存</button>
				  </div>
			</div>
			<div  id="editor-div"  class="panel-body" data-articledraftid="${articleDraftId}">
	  </div>
 </div>
</@page>