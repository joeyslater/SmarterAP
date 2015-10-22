angular.module('smarterap')

.controller("HeaderController", HeaderController);

function HeaderController($http, Ui) {
    var ctrl = this;
    ctrl.headerTitle = Ui.getHeaderTitle;
    ctrl.user = Ui.getUser;

    ctrl.logout = function() {
        $http.get('/logout')
            .then(function() {
                console.log('success');
            })
            .then(function() {
                console.log('failure');
            });
    };
}
