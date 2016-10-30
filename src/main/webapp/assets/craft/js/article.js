/**
 * 文章详情页js，依赖jquery,comments
 */
requirejs(function() {
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
	
	if ($(".blog-post").data("id")==37) {
		initLove()
	}
});
var likeCount=0;
var second=0;
function timer(){
	$("#likeCount").html(++second);
	if (second==14) {
		location.href="https://chulung.com/myLove";
	}
}
function initLove(){
	$(".heart").click(function(){
		likeCount++;
		if (likeCount==13) {
			$(".heart").addClass("heartAnimation");
			setInterval("timer()",1000);  
			$(".heart").off();
		}
	});
	
	  $("#btn-drag").click(function(){  
        	$("#p1").hide();
			$("#p2").show(3000);
			$("#btn-drag").hide(3000);
	  });  
	    var pNum=1;
	    $("#btn-peek").click(function(){
	    	var p;
	    	switch(pNum%3)
	    	{
	    	case 1:
	    		p="他出发的<b>日期</b>，他和她初见的<b>日期</b>。"
	    	  break;
	    	case 2:
	    		p="在计算机底层原理中，所有的加减乘除运算都可以用<b>加法</b>来表示。"
	    	  break;
	    		break;
	    	case 0:
	    		p="♥"
	    			break;
	    	default:
	    		p="你啥也没看到。";
	    	}
	    	pNum++;
	    	$("#p-peek").html(p);
	    });
	    
}
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
							max : 50,
							message : '邮箱长度不能超过50个字符.'
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
					if (data.success == 1) {
						window.location.reload();
					}else{
						alert(data.message);
					}
					 
				}
			});
		});
	});
}