'use strict';

module.exports = {
	files: {
		src: [
			'<%=concat.buildCSS.dest%>',
			'<%=concat.compileJS.dest%>'
		]
	}
};
