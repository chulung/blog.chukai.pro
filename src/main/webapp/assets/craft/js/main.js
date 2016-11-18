// contents of main.js:
require
		.config({
			bathUrl : '/assets/craft/js/',
		});

// Start the main app logic.
/**
 * module 当前模块自定义js,值来自于页面
 */
requirejs([ 'sidebarInfo','global','lib/tmpl', module], function(sidebarInfo,
		global, module) {
	global.init();
	sidebarInfo.init();
	// module js存在则尝试调用初始化方法
	module && module.init && module.init();
});