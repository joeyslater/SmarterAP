'use strict'

var basePath = require('path').resolve('build/tasks');

require('fs').readdirSync(basePath).forEach(function(file) {
	if (file.match(/.+\.js/g) !== null) {
		var name = file.replace('.js', '');
		exports[name] = require(basePath + '/' + file);
	}
});
