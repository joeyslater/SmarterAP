'use strict';

/**
 * Minify the sources!
 */
module.exports = {
	compile: {
		options: {
			sourceMap: true,
			sourceMapIncludeSources: true,
			sourceMapIn: '<%= concat.compileJS.dest %>.map',
			sourceMapName: '<%= buildConfig.compileDir %>/main.js.map'
		},
		files: {
			'<%= concat.compileJS.dest %>': '<%= concat.compileJS.dest %>'
		}
	}
};
