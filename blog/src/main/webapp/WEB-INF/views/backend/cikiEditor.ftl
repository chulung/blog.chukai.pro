<#include "WEB-INF/views/backend/pageMacro.ftl">     
<@page mainJs="editor-main" miancss=["/markdown/lib/codemirror/codemirror.min.css","/markdown/css/editormd.css"]>
<div class="row">
	<div id="editor-tree" class="col-md-2"></div>
	<div class="col-md-10">
		<div class="panel panel-default">
			<div class="panel-heading editor">
			  	  <div id="div-blog" class="panel-title">
			      		<lable id="lb-cate"></lable>
				  </div>
				  <div><button id="btn-save">保存</button></div>
			</div>
			<div  id="editor-div"  class="panel-body" ></div>
		 </div>
		 <div id="context-menu">
	      <ul class="dropdown-menu" role="menu">
	        <li><a tabindex="-1" href="#" data-method="post">新增</a></li>
	        <li><a tabindex="-1" href="#" data-method="put">编辑</a></li>
	        <li><a tabindex="-1" href="#" data-method="delete">删除</a></li>
	      </ul>
	    </div>
	</div>
</div>
<div id="modal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="标题目录">
  <div class="modal-dialog modal-sm">
      <div class="modal-content">

        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
          <h4 class="modal-title" id="mySmallModalLabel">Title</h4>
        </div>
        <div class="modal-body">
         <form action="/backend/ciki" method="post">
          <div class="form-group">
            <input type="hidden" class="form-control" name="id">
            <input type="text" class="form-control" name="title">
          </div>
         </form>
        </div>
        <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary">提交</button>
      </div>
      </div><!-- /.modal-content -->
    </div>
</div>
</@page>