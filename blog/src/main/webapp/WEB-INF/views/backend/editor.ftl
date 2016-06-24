<#include "WEB-INF/views/backend/pageMacro.ftl">     
<@page mainJs="editor" miancss=["/markdown/lib/codemirror/codemirror.min.css","/markdown/css/editormd.css"]>
<div class="col-md-2">.col-md-8</div>
  
<div class="col-md-10">
	<div class="panel panel-default">
				<div class="panel-heading editor">
				  	  <div class="panel-title">
				  	  		<select id="slc-site">
								<option value="blog">blog</option>
								<option value="ciki">ciki</option>
				  	  		</select>
					  </div>
				  	  <div id="div-blog" class="panel-title">
							<select id="typeId">
							<#list articleTypes as type>
								<option value="${type.dictValue}">${type.dictDesc}</option>
							</#list>
							</select>
				      		<lable>标题<lable>
				      		<input id="title" class="title" type="text"/> 
				      		<input id="isPublish" type="checkBox" checked>发布</input>
					  </div>
				  	  <div id="div-ciki" class="panel-title none">
				  	  		<select id="slc-ciki-h1">
				  	  		<option>请选择</option>
				  	  		</select>
				  	  		<select id="slc-ciki-h2">
				  	  		<option>请选择</option>
				  	  		</select>
				  	  		<select id="slc-ciki-article">
				  	  		<option>请选择</option>
				  	  		</select>
				  	  		
					  </div><button id="btn-save">保存</button>
				</div>
				<div  id="editor-div"  class="panel-body" data-articledraftid="${articleDraftId}">
		  </div>
	 </div>
</div>
</@page>