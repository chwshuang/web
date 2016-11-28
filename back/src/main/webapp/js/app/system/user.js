/*!
 * JavaScript - loadGoogleMaps( version, apiKey, language )
 *
 * - Load Google Maps API using jQuery Deferred. 
 *   Useful if you want to only load the Google Maps API on-demand.
 * - Requires jQuery 1.5
 * 
 * Copyright (c) 2011 Glenn Baker
 * Dual licensed under the MIT and GPL licenses.
 */
/*globals window, google, jQuery*/
var loadUsersTables = (function($) {
	$('#table').bootstrapTable({
		url: 'vendor/jquery/datatables/datatable.json',
		columns: [{
			field: 'engine',
			title: '用户编号'
		}, {
			field: 'browser',
			title: '用户名称'
		}, {
			field: 'platform',
			title: '用户权限'
		}, {
			field: 'version',
			title: '创建时间'
		}, {
			field: 'grade',
			title: '操作'
		}  ]
	});
}(jQuery));
