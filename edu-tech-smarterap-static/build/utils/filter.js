'use strict'

var Filter = module.exports;

Filter.forJS = function(files) {
	return files.filter(function(file) {
		return file.match(/\.js$/);
	});
};

Filter.forCSS = function(files) {
	return files.filter(function(file) {
		return file.match(/\.css$/);
	});
};
