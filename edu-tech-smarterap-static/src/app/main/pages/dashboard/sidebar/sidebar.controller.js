angular.module('smarterap')

.controller("SidebarController", SidebarController);

function SidebarController($state, $mdSidenav, Ui, UserService) {
    var ctrl = this;

    ctrl.isOnlyStudent = function() {
        if (UserService.getUser()) {
            return JSON.stringify(['STUDENT']) === JSON.stringify(UserService.getUser().securityRoles);
        } else {
            return false;
        }
    };
}
