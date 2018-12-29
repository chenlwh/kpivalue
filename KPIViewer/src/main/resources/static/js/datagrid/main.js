(function(factory) {
	if (typeof define === "function" && define.amd) {
		// AMD. Register as an anonymous module.
		require.config({
			paths : {
				'HteOS' : 'hteos'
			}
		});
		//HteOS.js作为全局依赖，需要优先加载，后续组件放在callback中加载。
		require(['HteOS/HteOS'], function(){
			require(["HteOS/EventManager",'HteOS/Template',
				"HteOS/Bootstrap","HteOS/UA"],factory);
		});
	} else {
		// Browser globals
		factory();
	}
}(function() {
	$(function(){
		HteOS.EventManager.trigger("hteos.ready");
		HteOS.Bootstrap.start();
	});
	return HteOS;
}));
