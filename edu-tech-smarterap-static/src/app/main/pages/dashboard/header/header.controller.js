angular.module('smarterap')

.controller("HeaderController", HeaderController);

function HeaderController($http, $state, Ui, $mdSidenav) {
    var ctrl = this;
    ctrl.headerTitle = Ui.getHeaderTitle;
    ctrl.user = Ui.getUser;

    ctrl.openSideNav = function() {

    };

    ctrl.changeDashboard = function(role) {
        Ui.setRole(role);
        $state.go('dashboard' + role.toLowerCase());
    };

    ctrl.logout = function() {
        $http.get('/smarter-ap/logout');
        $state.go("login");
    };

    ctrl.toggleSideNavigation = function() {
        $mdSidenav('left-nav').toggle();
    };
}
