
/**
 * 博客首页，依赖侧边栏sidebarInfo.js
 */
requirejs([ 'jquery', 'sidebarInfo' ], function($, sidebarInfo) {
	sidebarInfo.init();
});