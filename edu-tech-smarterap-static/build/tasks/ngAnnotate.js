'use strict';

module.exports = {
	target: {
		src: ['<%= concat.compileJS.dest %>'],
		dest: '<%= concat.compileJS.dest %>'
	}
};
