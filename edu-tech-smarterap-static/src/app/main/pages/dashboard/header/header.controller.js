angular.module('smarterap')

.controller("HeaderController", HeaderController);

function HeaderController($rootScope, $http, $state, Ui, UserService, $mdSidenav, STORMPATH_CONFIG) {
    var ctrl = this;
    ctrl.headerTitle = Ui.getHeaderTitle;
    ctrl.user = UserService.getUser;

    ctrl.openSideNav = function() {
        $mdSidenav('left-nav').open();
    };

    ctrl.changeDashboard = function(role) {
        UserService.setRole(role);
        $state.go(UserService.getDashboard());
    };

    ctrl.logout = function() {
        $http.get('/smarter-ap/logout').then(function() {
            $state.go("login");
            UserService.setUser();
            $rootScope.$broadcast(STORMPATH_CONFIG.SESSION_END_EVENT);
        });
    };

    ctrl.toggleSideNavigation = function() {
        $mdSidenav('left-nav').toggle();
    };
}
