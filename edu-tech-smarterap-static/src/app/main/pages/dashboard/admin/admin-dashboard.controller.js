angular.module('smarterap')

.controller("AdminDashboardController", AdminDashboardController);

function AdminDashboardController($http, $location) {
    var ctrl = this;

    ctrl.tabs = [{
        'title': 'Users'
    }, {
        'title': 'Subjects'
    }, {
        'title': 'Questions'
    }, {
        'title': 'Database'
    }];
}
