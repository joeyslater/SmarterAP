'use strict';

//Module for the application
angular.module('smarterap', [
    'templates-app',
    'templates-common',
    'ngMaterial',
    'ngAnimate',
    'ngAria',
    'ui.router',
    'md.data.table',
    'textAngular'
])

.config(function($provide) {
    $provide.decorator('taOptions', ['taRegisterTool', '$delegate', function(taRegisterTool, taOptions) {
        taRegisterTool('code', {
            display: '<span>Test</span>'
        });
        taOptions.toolbar[1].push('code');

        console.log(taOptions.toolbar);
        return taOptions;
    }]);
})

.controller('AppCtrl', AppCtrl);

function AppCtrl() {}
