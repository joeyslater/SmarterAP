angular.module('smarterap')

.controller("AdminUsersListController", AdminUsersListController);

function AdminUsersListController($http, $q, $timeout) {
    var ctrl = this;
    ctrl.loadingUsers = true;
    ctrl.selected = [];
    ctrl.listQuery = {
        'order': 'surname'
    };

    init();

    ctrl.onOrderChange = function(order) {
        var deferred = $q.defer();
        $timeout(function() {
            deferred.resolve();
        }, 10);
        return deferred.promise;
    };


    function init() {
        var userDeferred = $q.defer();

        $http.get('/smarter-ap/api/admin/users')
            .then(
                function(response) {
                    ctrl.users = response.data;
                    ctrl.loadingUsers = false;
                    userDeferred.resolve(ctrl.users);
                },
                function(response) {
                    ctrl.user = [];
                    ctrl.loadingUsers = false;
                });
        ctrl.userDeferred = userDeferred.promise;
    }
}
