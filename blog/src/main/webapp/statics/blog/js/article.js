/**
 * 文章详情页js，依赖jquery,comments
 */
requirejs([ "jquery", 'formValidation_bootstrap' ], function($) {
	boundSubmitComments();
	$.ajax({
		url : '/comments/list/' + $("input[name='articleId']").val(),
		type : 'get',
		dataType : 'json',
		success : function(data) {
			$comments = $(".list-group-item.comments-li.none")
			$.each(data.list, function(i, item) {
				$nCom = $comments.clone().removeClass("none");
				$nCom.find(".floor").attr({'name':item.id,'href':'#'+item.id}).html("#"+(++i));
				$nCom.find(".date").html(item.createTime);
				$nCom.find(".name").html(item.userName);
				$nCom.find(".list-group-item-text").html(item.comment);
				$comments.before($nCom);
			});
		}
	});
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
							message : '不超过10个字'
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
				data : $(e.target).serialize(),
				success : function(data) {
					data.success == 1 && window.location.reload();
				}
			});
		});
	});
}