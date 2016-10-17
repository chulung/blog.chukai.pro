//contents of main.js:
require
		.config({
			bathUrl : 'https://chulung.com/statics/blog/js',
			paths : {
				jquery : 'https://apps.bdimg.com/libs/jquery/1.11.3/jquery.min',
				bootstrap : 'https://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min',
				formValidation : 'https://cdn.bootcss.com/formvalidation/0.6.1/js/formValidation.min',
				formValidation_bootstrap : 'https://cdn.bootcss.com/formvalidation/0.6.1/js/framework/bootstrap.min'
			},
			// bootstrap依赖jquery，手动声明依赖
			shim : {
				bootstrap : [ 'jquery' ],
				formValidation:['bootstrap'],
				formValidation_bootstrap : [ 'formValidation' ],
			}
		});

// Start the main app logic.
/**
 * module 当前模块自定义js,值来自于页面
 */
requirejs([ 'jquery', 'bootstrap',], function($, bootstrap) {
	$(".nav-container").hide();
	$("body").css("background-color","#ffb6c1"); 
	$(".blog-footer").hide();
	lis=$(".timeline").find("li");
	index=lis.length-1;
	$("html,body").click(function(){
		if ((++click)==3) {
			setTimeout("foo()",3000);
		}
	});
});

var lis;
var index;
var click=0;
function foo(){
	if (index>0) {
		$(lis[index]).show(2000);
		index--;
		setTimeout("foo()",3000);
	}else if(index==0){
		click=0;
		$("html,body").off().click(function(){
			if ((++click)==3) {
				$(lis[0]).show(2000);
			}
		});
	}
	$("html, body").animate({scrollTop: 0},  'slow'); 
}