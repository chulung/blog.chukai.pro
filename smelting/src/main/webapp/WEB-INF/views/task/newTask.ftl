<#include "WEB-INF/views/baseMacro.ftl">     
<@base base_title="新建任务" base_js=["/global/js/moment-with-locales.js","/global/js/bootstrap-datetimepicker.min.js","/smelting/js/newtask.js"] base_css=['/global/css/bootstrap-datetimepicker.min.css'] base_keywords="熔炼,初的博客">
<div class="container">
  	 <div class="panel panel-primary">
			<!-- Default panel contents -->
			<div class="panel-heading">新增任务</div>
			<div class="panel-body">
	        <form id="installationForm" action="/smelting/task" method="post">
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">任务名:</label>
	            <input type="text" class="form-control" id="recipient-name" name="taskName">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">任务描述:</label>
	            <textarea class="form-control" id="message-text" name="taskDesc"></textarea>
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">积分:</label>
	            <input type="text" class="form-control" id="recipient-name" name="completePoint">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">任务类型:</label>
		          <label class="radio-inline">
				  <input type="radio" name="taskType"  value="1" checked> 团队
				  </label>
				  <label class="radio-inline">
				  <input type="radio" name="taskType" value="2"> 个人
				  </label>
	          </div>
	            <div class="form-group">
	            <label for="message-text" class="control-label">截止时间:</label>
	 			 <div class="form-group datetime">
	                <div class='input-group date' id='datetimepicker'>
	                    <input type='text' class="form-control" readonly  name="endTime" />
	                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span>
	                    </span>
	                </div>
	            </div>
	          </div>
	      </div>
	      <div class="panel-footer">
	        <button type="submit" class="btn btn-primary">保存</button>
	        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		  </div>
		</form>
 		</div>
  </div>
</div>
</@base>
