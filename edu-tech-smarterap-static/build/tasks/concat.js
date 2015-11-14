'use strict'

var removeUseStrict = function(src) {
    return src.replace(/(^|\n)[ \t]*('use strict'|"use strict");?\s*/g, '$1');
};

module.exports = {
    /**
     * The `buildCSS` target concatenates compiled CSS and vendor CSS together.
     */
    buildCSS: {
        options: {
            process: removeUseStrict
        },
        src: [
            '<%= buildConfig.buildDir %>/main.css'
        ],
        dest: '<%= buildConfig.compileDir %>/main.css'
    },
    /**
     * The `compileJS` target is the concatenation of our application source code and all specified vendor source code into a single file.
     */
    compileJS: {
        options: {
            process: removeUseStrict,
            sourceMap: true
        },
        src: [
            '<%= buildConfig.vendorFiles.js %>',
            './build/module.prefix',
            '<%= buildConfig.buildDir%>/src/**/*.js',
            '<%= html2js.app.dest %>',
            '<%= html2js.common.dest %>',
            './build/module.suffix'
        ],
        dest: '<%= buildConfig.compileDir %>/main.js'
    }

};
