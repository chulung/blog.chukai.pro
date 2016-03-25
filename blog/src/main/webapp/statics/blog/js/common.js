
$(function() {
	commonInfo();
});
function commonInfo() {
	$.ajax({
		type : "GET",
		url : "/commonInfo",
		dataType : "json",
		success : function(data) {
			if (data.articleFilings!=null) {
				$.each(data.articleFilings,function(i,item){
					var ym = item.yearMonth.year+'-'+item.yearMonth.monthValue;
					$("#articleFilings").append("<li><a href='/monthFilings/"+ym+"'>" + ym + '</a>' + '(' + item.count + ')' + '</li>');
				});
			}
		}
	});

}