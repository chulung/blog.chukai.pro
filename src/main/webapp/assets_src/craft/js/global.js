/**
 * 依赖jquery和bootstrap.js
 */
define(function() {
	// // 扩展jquery ajax 统一处理异常
	// // 备份jquery的ajax方法
	// var _ajax = $.ajax;
	// // 重写jquery的ajax方法
	// $.ajax = function(opt) {
	// 	// 备份opt中error和success方法
	// 	var fn = {
	// 		error : opt.error,
	// 		success : opt.success
	// 	}
	// 	// 扩展增强处理
	// 	var _opt = $.extend(opt, {
	// 		error : function(XMLHttpRequest, textStatus, errorThrown) {
	// 			// 错误方法增强处理
	// 			fn.error && fn.error(XMLHttpRequest, textStatus, errorThrown);
	// 		},
	// 		success : function(data, textStatus) {
	// 			// 成功回调方法增强处理
	// 			// success标识存在且不为1则标识请求异常
	// 			data.success && data.success != 1
	// 					&& alert(data.message || "请求失败!");
	// 			fn.success && fn.success(data, textStatus);
    //
	// 		}
	// 	});
	// 	_ajax(_opt);
	// };
	// return an object to define the module.
	var exports = {};
	// 彩蛋
	exports.clickCount = 0;
	exports.easterEggs = function() {
		$("html,body").click(
				function(e) {
					var $i;
					var x = e.pageX, y = e.pageY;
						var obj = document.elementFromPoint(e.clientX,
								e.clientY);
						 if ($(obj).html().length <= 20) {
							$i = $("<b>").text(
									"+" + (++exports.clickCount) + " Hits! "
											+ $(obj).html() + "!");
						} else {
							$i = $("<b>").text(
									"+" + (++exports.clickCount) + " Hits!");
						}
					$i.css({
						top : y - 20,
						left : x,
						position : "absolute",
						color : "#1692e4"
					});
					$("body").append($i);
					$i.animate({
						top : y - 180,
						opacity : 0,
						"font-size" : "1.4em"
					}, 1500, function() {
						$i.remove();
					});
					e.stopPropagation();
				});
	};
	// 用户访问信息收集
	exports.tracker = function() {
		$.ajax({
			url : "/tracker/userTracker",
			data : {
				"referer" : document.referer,
				"href" : window.location.href
			},
			type : "post"
		});
	};
	exports.init = function() {
		exports.easterEggs();
		exports.tracker();
		if(navigator.userAgent.match(/MicroMessenger/i)){
			var weixinShareLogo ='https://static.chulung.com/assets/theme/img/logo.jpg';
			$('body').prepend('<div style=" overflow:hidden; width:0px; height:0; margin:0 auto; position:absolute; top:-800px;"><img src="'+ weixinShareLogo +'"></div>')

}
    };
	return exports;
});
