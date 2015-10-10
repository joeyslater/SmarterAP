'use strict';

module.exports = {
	options: {
		map: true,
		processors: [
			require('autoprefixer')({
				browsers: ['> 5%', 'last 2 version', 'ie > 8', 'bb >= 7', 'ff >= 3', 'Opera >= 10.11']
			})
		]
	},
	dist: {
		src: '<%= buildConfig.buildDir %>/main.css'
	}
};
