'use strict';

/**
 * Runs a connect server. The main item to be concerned with is the proxy server configuration
 */
module.exports = {
	proxies: '<%= buildConfig.proxies %>',

	livereload: {
		options: {
			livereload: '<%= ports.livereload %>',
			port: '<%= ports.connect %>',
			// Change this to '0.0.0.0' to access the server from outside.
			hostname: 'localhost',
			middleware: function(connect, options, middlewares) {
				middlewares.unshift(require('grunt-connect-proxy/lib/utils').proxyRequest);
				// Serve static files.
				// middlewares.push(connect.static(require('path').resolve('target')));
				// middlewares.push(connect.static(require('path').resolve('vendor')));
				// middlewares.push(connect.static(require('path').resolve('node_modules')));
				return middlewares;
			}


		}
	}
};
