<#include "WEB-INF/views/backend/pageMacro.ftl">     
<@page mainJs="editor" miancss=["/markdown/lib/codemirror/codemirror.min.css","/markdown/css/editormd.css"]>
<div class="panel panel-default">
			<div class="panel-heading">
			  	  <div class="panel-title">
			      		<lable>标题<lable>
			      		<input id="title" class="title" type="text"/> 
			      		<input id="isPublish" type="checkBox" checked>发布</input>
						<select id="typeId">
						<#list articleTypes as type>
							<option value="${type.dictValue}">${type.dictDesc}</option>
						</#list>
						</select>
						<select >
						</select >
						<select >
						</select >
						<button id="btn-save">保存</button>
				  </div>
			</div>
			<div  id="editor-div"  class="panel-body" data-articledraftid="${articleDraftId}">
	  </div>
 </div>
</@page>