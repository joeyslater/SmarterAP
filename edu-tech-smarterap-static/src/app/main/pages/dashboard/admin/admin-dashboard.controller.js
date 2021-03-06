angular.module('smarterap')

.controller("AdminDashboardController", AdminDashboardController);

function AdminDashboardController($http, $location, Ui) {
    var ctrl = this;

    ctrl.tabs = [{
        'title': 'Users',
        'template': 'main/pages/dashboard/admin/users/users-list.tpl.html'
    }, {
        'title': 'Subjects',
        'template': 'main/pages/dashboard/admin/subjects/subjects-list.tpl.html'
    }, {
        'title': 'Tags',
        'template': 'main/pages/dashboard/admin/tags/tags-list.tpl.html'
    }];

    Ui.setHeaderTitle('Admin Dashboard');
}
