angular.module('smarterap')

.controller("SidebarController", SidebarController);

function SidebarController($state, $mdSidenav) {
    var ctrl = this;

    ctrl.goTo = function(state) {
        $state.go(state);
        $mdSidenav('left-nav').toggle();
    };
}
