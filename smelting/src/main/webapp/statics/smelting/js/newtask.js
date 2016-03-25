$(function() {
	$('#datetimepicker').datetimepicker({
		format:'yyyy-mm-dd hh:ii:ss'
	}).on('changeDate', function(ev){
		// 改变时需触发日期输入事件，用于校验框架
	     $("input[name='endTime']").val($("input[name='endTime']").val()+":00").trigger("input");
	});;

	$('#installationForm')
			.formValidation(
					{
						framework : 'bootstrap',
						icon : {
							valid : 'glyphicon glyphicon-ok',
							invalid : 'glyphicon glyphicon-remove',
							validating : 'glyphicon glyphicon-refresh'
						},
						// This option will not ignore invisible fields which
						// belong to inactive panels
						excluded : ':disabled',
						fields : {
							taskName : {
								validators : {
									notEmpty : {
										message : '请输入任务名.'
									}
								}
							},
							taskDesc : {
								validators : {
									notEmpty : {
										message : '请输入任务描述信息.'
									}
								}
							},
							endTime : {
								validators : {
									notEmpty : {
										message : '请选择日期.'
									}
								}
							},
							completePoint : {
								validators : {
									notEmpty : {
										message : '请输入完成积分.'
									},
									digits:{
										message : '必须是整数哦.'
									}
								}
							}
						}
					});
	
});