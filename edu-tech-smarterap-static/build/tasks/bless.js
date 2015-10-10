'use strict'

module.exports = {
	css: {
		options: {
			force: true
		},
		files: {
			'<%= buildConfig.compileDir %>/main.css': '<%= buildConfig.compileDir %>/main.css'
		}
	}
};
