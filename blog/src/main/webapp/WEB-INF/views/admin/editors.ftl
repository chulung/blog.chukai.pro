<#include "WEB-INF/views/admin/pageMacro.ftl">     
<@indexPage mianJs=["/markdown/editormd.js","/admin/js/edit.js"] miancss=["/markdown/css/editormd.css"]>
		<div class="col-md-12">
	        <div class="content-box-large">
	          <div class="panel-heading">
	          	  <div class="panel-title">Markdown</div>
	          	  <div class="panel-title">
		          		<lable>标题<lable>
		          		<input id="title" class="title" type="text"/> 
		          		<input id="isPublish" type="checkBox" checked>发布</input>
						<select id="typeId">
						<#list articleTypes as type>
							<option value="${type.id}">${type.typeName}</option>
						</#list>
						</select>
						<button id="btn-save">保存</button>
				  </div>
		          <div class="panel-options">
		            <a href="#" data-rel="collapse"><i class="glyphicon glyphicon-refresh"></i></a>
		            <a href="#" data-rel="reload"><i class="glyphicon glyphicon-cog"></i></a>
		          </div>
	      	  </div>
	          <div  id="editor-div"  class="panel-body" data-articledraftid="${articleDraftId}">
	          </div>
      		</div>
		</div>
</@indexPage>