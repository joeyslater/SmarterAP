'use strict';

module.exports = {
	options: {
		processors: [
			require('autoprefixer-core')({
				browsers: ['> 5%', 'last 2 version', 'ie > 8', 'bb >= 7', 'ff >= 3', 'Opera >= 10.11']
			}).postcss
		]
	},
	dist: {
		src: [
			'<%= buildConfig.buildDir %>/main.css'
		]
	}
};
