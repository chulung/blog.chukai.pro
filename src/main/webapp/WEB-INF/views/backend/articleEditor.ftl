<#include "WEB-INF/views/backend/pageMacro.ftl">     
<@page mainJs="editor-main" miancss=["/markdown/lib/codemirror/codemirror.min.css","/markdown/css/editormd.css"]>
<div class="col-md-12">
          <div class="panel-heading">
          	  <div class="panel-title">Markdown</div>
          	  <div class="panel-title">
	          		<lable>标题</lable>
	          		<input id="title" class="title" type="text"/>
					<input id="isPublish" type="checkBox" checked>发布</input>
					<select id="typeId">
							<#list articleTypes as type>
							<option value="${type.dictValue}">${type.dictDesc}</option>
							</#list>
					</select>
					<button id="btn-save" data-articledraftid="${articleDraftId}">保存</button>
			  </div>
              <div>
                  <lable>license</lable>
                  <input id="license" type="text"/>
			  </div>
		  </div>
          <div  id="editor-div"  class="panel-body" >
          </div>
</div>
</@page>