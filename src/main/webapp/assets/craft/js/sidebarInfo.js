/**
 * 侧边栏信息，依赖jquery
 */
define(function() {
	return {
		init : function() {
			$.ajax({
				type : "GET",
				url : "/sidebarInfo",
				dataType : "json",
				success : function(data) {
					if (data.articleFilings != null) {
						$.each(data.articleFilings, function(i, item) {
							var ym = item.yearMonth.year + '-'
									+ item.yearMonth.monthValue;
							$("#articleFilings").append(
									"<li><a href='/monthFilings/" + ym + "'>"
											+ ym + '</a>' + '(' + item.count
											+ ')' + '</li>');
						});
					}
					var nLi = $("#pop-art-ul").find(".none");
					$.each(data.popularArticles,(function () {
						$li=nLi.clone().show().appendTo($("#pop-art-ul"));
						$li.find(".entry-title").append($("<a>",{
							ref:"bookmark",
							href:"/article/"+this.id,
							html:this.title
						}));
						$li.find(".entry-category").append($("<a>",{
							href:"/articles/page/1?typeId="+this.typeId+"#content",
							html:this.typeName
						}));
						$li.find(".entry-datetime").html(this.createTime);
					}));
					nli=$("#recentcomments").find(".none");
					$.each(data.recentlyComments,(function () {
						$li=nLi.clone().html("<span class='comment-author-link'>"+
							this.userName
							+"</span>on <a href='/article/"+this.articleId+"#comments"+this.id+"'></a>").show().appendTo($("#recentcomments"));
					}));
				}
			});
		}
	}
});