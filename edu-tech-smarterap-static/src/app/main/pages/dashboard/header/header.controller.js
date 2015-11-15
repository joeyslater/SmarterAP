angular.module('smarterap')

.controller("HeaderController", HeaderController);

function HeaderController($rootScope, $http, $state, Ui, $mdSidenav, STORMPATH_CONFIG) {
    var ctrl = this;
    ctrl.headerTitle = Ui.getHeaderTitle;
    ctrl.user = Ui.getUser;

    ctrl.openSideNav = function() {

    };

    ctrl.changeDashboard = function(role) {
        Ui.setRole(role);
        $state.go('dashboard.' + role.toLowerCase());
    };

    ctrl.logout = function() {
        $http.get('/smarter-ap/logout').then(function() {
            $state.go("login");
            Ui.setUser();
            $rootScope.$broadcast(STORMPATH_CONFIG.SESSION_END_EVENT);
        });
    };

    ctrl.toggleSideNavigation = function() {
        $mdSidenav('left-nav').toggle();
    };
}
