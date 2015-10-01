'use strict'

module.exports = function(grunt) {

	grunt.loadTasks('build/tasks/custom');

	grunt.registerTask('help', 'View available list of tasks', ['availabletasks']);

	//Renames watch to delta (change in code)
	grunt.renameTask('watch', 'delta');
	grunt.registerTask('watch', ['delta']);

	grunt.registerTask('default', 'Default task is compile', ['compile']);

	// The `build` task gets your app ready to run for development and testing.
	grunt.registerTask('build', 'Build application assets', [
		'clean',
		'html2js',
		'jshint',
		'sass',
		'postcss',
		'copyContent',
		'indexHTML:build'
	]);

	grunt.registerTask('test', 'Sets up a test server to run once.', [
		'html2js',
		'karmaConfig',
		'karma:unit'
	]);

	grunt.registerTask('testLoop', 'Run a test server in the background, watching for changes', [
		'build',
		'karmaConfig',
		'karma:loop'
	]);

	grunt.registerTask('serve', 'Boots up a local node serve and watches source files for changes.', [
		'build',
		'configureProxies:server',
		'connect:livereload',
		'karmaConfig',
		'karma:loopBackground',
		'watch'
	]);

	grunt.registerTask('compile', 'Concat, minify, and compile the application assets', [
		'build',
		'copy:vendorAssetsCompile',
		'copy:appAssetsCompile',
		'concat:compileJS',
		'concat:buildCSS',
		'ngAnnotate',
		'uglify',
		'cssmin',
		'bless',
		'filerev',
		'fixSourceMap',
		'indexHTML:compile'
	]);

	grunt.registerTask('copyContent', 'Comprises each of the copy tasks for application', [
		'copy:appAssets',
		'copy:appJS',
		'copy:vendorAssets',
		'copy:vendorJS',
		'copy:vendorCSS'
	]);


	//Alias for testing
	grunt.registerTask('testOnce', ['test']);
	grunt.registerTask('testContinuous', ['testLoop']);
}
