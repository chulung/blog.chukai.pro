<#include "WEB-INF/views/backend/pageMacro.ftl">     
<@page mainJs="editor" miancss=["/markdown/lib/codemirror/codemirror.min.css","/markdown/css/editormd.css"]>
<div id="editor-tree" class="col-md-2"></div>
  
<div class="col-md-10">
	<div class="panel panel-default">
		<div class="panel-heading editor">
		  	  <div id="div-blog" class="panel-title">
		      		<lable id="lb-cate"></lable>
		      		<lable>标题</lable>
		      		<input id="title" class="title" type="text"/> 
		      		<input id="isPublish" type="checkBox" checked>发布</input>
			  </div>
			  <div><button id="btn-save">保存</button></div>
		</div>
		<div  id="editor-div"  class="panel-body" ></div>
	 </div>
</div>
</@page>