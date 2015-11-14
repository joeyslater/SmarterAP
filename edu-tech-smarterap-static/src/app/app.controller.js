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
    'textAngular',
    'as.sortable',
    'ngSanitize',
    'stormpath'
])

.config(function($provide, STORMPATH_CONFIG) {
    $provide.decorator('taOptions', ['taRegisterTool', '$delegate', function(taRegisterTool, taOptions, taAppl) {
        taRegisterTool('code', {
            display: '<span>Test</span>'
        });
        taOptions.toolbar[1].push('code');
        taOptions.classes = {
            toolbarButton: 'md-flat md-button md-icon-button',
            toolbarButtonActive: 'md-primary',
            disabled: 'button-disabled'

        };

        return taOptions;
    }]);


    STORMPATH_CONFIG.AUTHENTICATION_ENDPOINT = "/smarter-ap/login";
    STORMPATH_CONFIG.CURRENT_USER_URI = "/smarter-ap/account";
    STORMPATH_CONFIG.REGISTER_URI = "/smarter-ap/register";

    //     $provide.decorator('taTools', ['$delegate', function(taTools) {
    //         taTools.bold.iconclass = 'icon-bold';
    //         taTools.italics.iconclass = 'icon-italic';
    //         taTools.underline.iconclass = 'icon-underline';
    //         taTools.ul.iconclass = 'icon-list-ul';
    //         taTools.ol.iconclass = 'icon-list-ol';
    //         taTools.undo.iconclass = 'icon-undo';
    //         taTools.redo.iconclass = 'icon-repeat';
    //         taTools.justifyLeft.iconclass = 'icon-align-left';
    //         taTools.justifyRight.iconclass = 'icon-align-right';
    //         taTools.justifyCenter.iconclass = 'icon-align-center';
    //         taTools.clear.iconclass = 'icon-ban-circle';
    //         taTools.insertLink.iconclass = 'icon-link';
    //         taTools.insertImage.iconclass = 'icon-picture';
    //         // there is no quote icon in old font-awesome so we change to text as follows
    //         delete taTools.quote.iconclass;
    //         taTools.quote.buttontext = 'quote';
    //         return taTools;
    //     }]);
})

.controller('AppCtrl', AppCtrl);

function AppCtrl() {}
