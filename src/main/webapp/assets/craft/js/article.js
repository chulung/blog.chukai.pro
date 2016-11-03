/**
 * 文章详情页js
 */
define(function() {
	var exports={};
		exports.init=function () {
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
		}
	return exports;
});
// 提交评论
function boundSubmitComments() {
	$('#submitComments').one("click", function() {
            if (!validate(["#comment","#userName"])){
                boundSubmitComments();
                return;
            }
			$.ajax({
				url : '/comments',
				type : 'post',
				dataType : 'json',
				data : $("#commentform").serialize(),
				success : function(data) {
					if (data.success == 1) {
						window.location.reload();
					}else{
						alert(data.message);
					}
					 
				}
			});
		});
}

function validate(ids){
    for(i=0;i<ids.length;i++){
        if (!$(ids[i]).val()){
            $(ids[i]).focus();
            return false;
        }
    }
    return true;
}