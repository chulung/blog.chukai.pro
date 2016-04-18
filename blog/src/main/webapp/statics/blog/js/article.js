/**
 * 文章详情页js，依赖jquery,comments
 */
requirejs([ "jquery", 'formValidation_bootstrap' ], function($) {
	boundSubmitComments();
});
// 提交评论
function boundSubmitComments() {
	$('#submitComments').one("click", function() {
		$("#comments-form").formValidation({
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
				userName : {
					validators : {
						notEmpty : {
							message : '请输入名字.'
						},
						stringLength : {
							max : 10,
							message : 'Cannot exceed 512 characters'
						}
					}
				},
				email : {
					validators : {
						notEmpty : {
							message : '请输入邮箱.'
						},
						emailAddress : {
							message : '邮箱格式不正确.'
						},
						stringLength : {
							max : 512,
							message : '邮箱长度不能超过512个字符.'
						}
					}
				},
				comment : {
					validators : {
						notEmpty : {
							message : '请输入评论内容.'
						},
						stringLength : {
							max : 300,
							message : '评论内容不能超过300个字.'
						}
					}
				}
			}
		}).on('success.form.fv', function(e) {

			e.preventDefault();// 不执行默认表单提交动作
			$.ajax({
				url : '/comments',
				type : 'post',
				dataType : 'json',
				data:$(e.target).serialize(),
				success : function(data) {
					data.success == 1 && window.location.reload();
				}
			});
		});
	});
}