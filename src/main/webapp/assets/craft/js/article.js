/**
 * 文章详情页js
 */
define(function() {
	var exports={};
    exports.boundSubmitComments=function () {
        $('#submitComments').one("click", function() {
            if (!validate(["#comment","#userName","#email"])){
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
                        exports.listComments();
                    }else{
                        alert(data.message);
                    }

                }
            });
        });
    }
    exports.listComments=function () {
        $.ajax({
            url : '/comments/list/' + $("input[name='articleId']").val(),
            type : 'get',
            dataType : 'json',
            success : function(data) {
                $("#ul_comments").find("li:visible").remove()
                var nLi=$("#ul_comments").find(".none");
                $.each(data.list,(function () {
                    $li=nLi.clone().html("<span class='comment-author-link'>"+
                        this.userName
                        +":</span> <p>"+this.comment+"</p>").show().appendTo($("#ul_comments"));
                }));
            }
        });
    }
    exports.init=function () {
        exports.boundSubmitComments();
        exports.listComments();
    }
	return exports;
});
function validate(ids){
    for(i=0;i<ids.length;i++){
        if (!$(ids[i]).val()){
            $(ids[i]).focus();
            return false;
        }
    }
    return true;
}