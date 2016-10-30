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
				}
			});
		}
	}
});