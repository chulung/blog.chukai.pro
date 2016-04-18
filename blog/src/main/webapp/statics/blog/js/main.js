// contents of main.js:
require
		.config({
			bathUrl : '/statics/blog/js',
			paths : {
				jquery : 'http://apps.bdimg.com/libs/jquery/1.11.3/jquery.min',
				bootstrap : '//cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min',
				formValidation : '//cdn.bootcss.com/formvalidation/0.6.1/js/formValidation.min',
				formValidation_bootstrap : '//cdn.bootcss.com/formvalidation/0.6.1/js/framework/bootstrap.min'
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
requirejs([ 'jquery', 'bootstrap', 'global', module ], function($, bootstrap,
		global, module) {
	global.init();
	// module js存在则尝试调用初始化方法
	module && module.init && module.init();
});