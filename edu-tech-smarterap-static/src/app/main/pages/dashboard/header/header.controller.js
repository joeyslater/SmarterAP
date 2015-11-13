angular.module('smarterap')

.controller("HeaderController", HeaderController);

function HeaderController($http, $state, Ui) {
    var ctrl = this;
    ctrl.headerTitle = Ui.getHeaderTitle;
    ctrl.user = Ui.getUser;

    ctrl.openSideNav = function() {

    };

    ctrl.logout = function() {
        $http.get('/smarter-ap/logout');
        $state.go("login");
    };
}

