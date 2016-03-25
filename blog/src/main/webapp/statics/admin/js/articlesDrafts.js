$(document).ready(function() {
	ajaxData(1);
});

function ajaxData(page){
	$.ajax({
		url:'/articleDrafts',
		type:'get',
		data:{'page':page,'pageSize':'10'},
		dataType:'json',
		success:function(data){
			$tbody=$('#articleTable').find('tbody');
			$(data).each(function(index,item){
				$tr=$('<tr>').appendTo($tbody);
				$('<th>').html(item.id).addClass('id').appendTo($tr);
				$('<th>').html(item.articleId).appendTo($tr);
				$('<th>').html(item.title).appendTo($tr);
				$('<th>').html(item.author).appendTo($tr);
				$('<th>').html(item.createTime).appendTo($tr);
				$('<th>').html(item.updateTime).appendTo($tr);
				$('<th>').html(item.mender).appendTo($tr);
				$('<th>').html(item.isPublish==1?"已发布":"未发布").appendTo($tr);
				$('<th>').addClass('version').html(item.version).appendTo($tr);
				$('<th>').append($('<a>').html('编辑').attr('href','/admin/editors?id='+item.id+"&version="+item.version)).appendTo($tr);
				$('<th>').append($('<a>').html('删除').attr('href','javascript:;').click(function(){
					if(confirm("确认删除")){
						$.ajax({
							url:"/articleDraft/"+$(this).parent().siblings('.id').html(),
							type:'delete',
							dataType:'json',
							success:function(data){
								if (data.success!=1) {
									alert(data.msg+":code "+data.suc);
									return;
								}
								window.location.reload();
							}
						});
					}
				})).appendTo($tr);
			});
			
		}
	});
}