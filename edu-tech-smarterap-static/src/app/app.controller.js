'use strict';

//Module for the application
angular.module('smarterap', [
    'templates-app',
    'templates-common',
    'ngMaterial',
    'ngAnimate',
    'ngAria',
    'ui.router'
])

.controller('AppCtrl', AppCtrl);

function AppCtrl() {}
