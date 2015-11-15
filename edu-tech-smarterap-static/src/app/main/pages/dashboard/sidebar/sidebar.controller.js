angular.module('smarterap')

.controller("SidebarController", SidebarController);

function SidebarController($state, $mdSidenav, Ui) {
    var ctrl = this;

    ctrl.goTo = function(state) {
        $state.go(state);
        $mdSidenav('left-nav').toggle();
    };

    ctrl.goToDashboard = function() {
        if (Ui.getRole()) {
            $state.go('dashboard.' + Ui.getRole().toLowerCase());
        }
        $mdSidenav('left-nav').toggle();
    };
}
