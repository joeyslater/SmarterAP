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
    'stormpath',
    'ui.bootstrap'
])

.config(function($provide, STORMPATH_CONFIG) {
    $provide.decorator('taOptions', ['taRegisterTool', '$delegate', function(taRegisterTool, taOptions, taAppl, taSelection) {
        taRegisterTool('code', {
            buttontext: 'Code',
            action: function() {
                return this.$editor().wrapSelection("formatBlock", '<div class="code">');
            }
        });
        taRegisterTool('comment', {
            buttontext: 'Code Comment',
            action: function() {
                return this.$editor().wrapSelection("formatBlock", '<div class="code comment">');
            }
        });
        taRegisterTool('normal', {
            buttontext: 'Normal',
            action: function() {
                return this.$editor().wrapSelection("formatBlock", "<div>");
            }
        });
        taOptions.toolbar = [
            ['bold', 'italics', 'underline', 'ul', 'ol', 'justifyLeft', 'justifyRight', 'justifyCenter'],
            ['normal', 'code', 'comment']
        ];
        taOptions.classes = {
            toolbarButton: 'md-flat md-button',
            toolbarButtonActive: 'md-primary',
            disabled: 'disabled'
        };

        return taOptions;
    }]);


    STORMPATH_CONFIG.AUTHENTICATION_ENDPOINT = "/smarter-ap/login";
    STORMPATH_CONFIG.CURRENT_USER_URI = "/smarter-ap/account";
    STORMPATH_CONFIG.REGISTER_URI = "/smarter-ap/register";
    STORMPATH_CONFIG.DESTROY_SESSION_ENDPOINT = "/smarter-ap/logout";

    $provide.decorator('taTools', ['$delegate', function(taTools) {
        taTools.bold.buttontext = '<md-icon class="material-icons">format_bold</md-icon>';
        taTools.bold.iconclass = undefined;
        taTools.italics.buttontext = '<md-icon class="material-icons">format_italics</md-icon>';
        taTools.italics.iconclass = undefined;
        taTools.underline.buttontext = '<md-icon class="material-icons">format_underline</md-icon>';
        taTools.underline.iconclass = undefined;
        taTools.ul.buttontext = '<md-icon class="material-icons">format_list_bulleted</md-icon>';
        taTools.ul.iconclass = undefined;
        taTools.ol.buttontext = '<md-icon class="material-icons">format_list_numbered</md-icon>';
        taTools.ol.iconclass = undefined;
        taTools.undo.buttontext = '<md-icon class="material-icons">undo</md-icon>';
        taTools.undo.iconclass = undefined;
        taTools.redo.buttontext = '<md-icon class="material-icons">redo</md-icon>';
        taTools.redo.iconclass = undefined;
        taTools.justifyLeft.buttontext = '<md-icon class="material-icons">format_align_left</md-icon>';
        taTools.justifyLeft.iconclass = undefined;
        taTools.justifyRight.buttontext = '<md-icon class="material-icons">format_align_right</md-icon>';
        taTools.justifyRight.iconclass = undefined;
        taTools.justifyCenter.buttontext = '<md-icon class="material-icons">format_align_centers</md-icon>';
        taTools.justifyCenter.iconclass = undefined;
        taTools.clear.buttontext = '<md-icon class="material-icons">format_clear</md-icon>';
        taTools.clear.iconclass = undefined;
        taTools.p.buttontext = 'Normal';
        taTools.p.iconclass = undefined;
        taTools.pre.buttontext = 'Code';
        taTools.pre.iconclass = undefined;
        taTools.pre.tooltiptext = "Code";
        return taTools;
    }]);

})

//Add insanely common functions to rootscope
.run(function($rootScope, $state, $mdSidenav, UserService) {

    //Used in almost every file
    $rootScope.goTo = function(state) {
        $state.go(state);
        $mdSidenav('left-nav').close();
    };

    $rootScope.getRole = function() {
        return UserService.getRole().toLowerCase();
    };
})

.controller('AppCtrl', AppCtrl)

;

function AppCtrl() {}
