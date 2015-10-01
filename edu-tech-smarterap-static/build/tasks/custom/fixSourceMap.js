'use strict';

var escapeRegExp = function(string) {
        return string.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, '\\$1');
    },
    replaceAll = function(string, find, replace) {
        return string.replace(new RegExp(escapeRegExp(find), 'g'), replace);
    };

// This is a hack, used to rewrite source map URLs after uglify + filerev; due to a bug in grunt-filerev
// Once this pull request is merged, it will no longer be necessary.
// https://github.com/yeoman/grunt-filerev/pull/69
module.exports = function(grunt) {

    grunt.task.registerTask('fixSourceMap', 'Replace reference to main.js in main.js.map', function() {
        if (!grunt.filerev || !grunt.filerev.summary) {
            grunt.fatal('Could not find grunt-filerev. Required task "filerev" must be run first.');
        }
        var options = this.options({
            dest: 'target/bin'
        });
        var mainJSName;
        Object.keys(grunt.filerev.summary).forEach(function(key) {
            if (key.toString().match(/.js$/)) {
                var normalizedPath = grunt.filerev.summary[key].replace(/\\/g, '/'),
                    startIndex = normalizedPath.indexOf('\\') > -1 ? normalizedPath.lastIndexOf('\\') + 1 : normalizedPath.lastIndexOf('/') + 1;
                mainJSName = normalizedPath.substring(startIndex);
            }
        });
        var compileDirectory = options.dest.replace(/\\/g, '/'),
            mainMapPath = compileDirectory + '/main.js.map',
            mainMapContents = grunt.file.read(compileDirectory + '/' + mainJSName + '.map'),
            updatedContents = replaceAll(mainMapContents, 'main.js', mainJSName);
        grunt.file.write(mainMapPath, updatedContents);
        var sourceMap = JSON.parse(mainMapContents);
        sourceMap.file = mainJSName;
        grunt.file.write(mainMapPath, JSON.stringify(sourceMap));
        grunt.file.delete(compileDirectory + '/' + mainJSName + '.map');
    });
};
